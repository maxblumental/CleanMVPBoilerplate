package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blumental.maxim.cleanmvp.interactor.Interactor;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V, L extends Lifecycle> implements Presenter<V, L> {

    protected final static String MEMENTO_KEY = "memento key";

    protected V view;

    protected CompositeSubscription interactorSubscriptions;

    protected CompositeSubscription lifecycleSubscription;

    protected Memento<BasePresenter<V, L>, ?> memento;

    public void setView(V view) {
        this.view = view;
    }

    @Override
    public <T extends LifecycleCallbackTrigger<L>> void observeLifecycle(Observable<T> observable) {
        lifecycleSubscription = new CompositeSubscription();

        Subscription subscription = observable.subscribe(
                new Action1<T>() {
                    @Override
                    public void call(T trigger) {

                        @SuppressWarnings("unchecked")
                        L lifecycle = (L) BasePresenter.this;

                        trigger.call(lifecycle);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(getClass().getSimpleName(),
                                "Caught exception while observing view's lifecycle...", throwable);
                    }
                }
        );

        lifecycleSubscription.add(subscription);
    }

    protected boolean isMementoNotEmpty() {
        return memento != null && memento.hasElement();
    }

    protected <R> void launchInteractor(Observable<R> interactorObservable,
                                        SubscriberFactory<?, R> factory) {

        AsyncSubject<R> asyncSubject = AsyncSubject.create();

        interactorObservable.subscribe(asyncSubject);

        MementoImpl<BasePresenter<V, L>, R> memento = new MementoImpl<>();

        @SuppressWarnings("unchecked")
        SubscriberFactory<BasePresenter<V, L>, R> factory1 =
                (SubscriberFactory<BasePresenter<V, L>, R>) factory;

        memento.store(asyncSubject, factory1);
        this.memento = memento;

        interactorSubscriptions.add(
                asyncSubject
                        .doOnCompleted(nullOutMemento())
                        .subscribe(factory1.create(this))
        );
    }

    protected <I, R> void launchInteractor(Interactor<I, R> interactor, I input,
                                           SubscriberFactory<?, R> factory) {

        Observable<R> interactorObservable = interactor.createObservable(input);

        launchInteractor(interactorObservable, factory);
    }

    @NonNull
    private Action0 nullOutMemento() {
        return new Action0() {
            @Override
            public void call() {
                if (memento != null) {
                    memento.clear();
                }
            }
        };
    }
}