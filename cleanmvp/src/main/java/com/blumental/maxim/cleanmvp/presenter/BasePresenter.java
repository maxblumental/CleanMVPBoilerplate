package com.blumental.maxim.cleanmvp.presenter;

import android.util.Log;

import com.blumental.maxim.cleanmvp.interactor.Interactor;
import com.blumental.maxim.cleanmvp.presenter.memento.InteractorMemento;
import com.blumental.maxim.cleanmvp.presenter.memento.InteractorResponseMemento;
import com.blumental.maxim.cleanmvp.presenter.memento.InteractorResponseMementoImpl;
import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V, L extends Lifecycle> implements Presenter<V, L> {

    protected V view;

    protected CompositeSubscription interactorSubscriptions;

    protected CompositeSubscription lifecycleSubscription;

    private InteractorMemento<BasePresenter<V, L>, ?> interactorMemento;

    private InteractorResponseMemento<BasePresenter<V, L>, ?> interactorResponseMemento;

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

    private boolean isInteractorResponseMementoNotEmpty() {
        return interactorResponseMemento != null && interactorResponseMemento.isNotEmpty();
    }

    protected <I, R> void launchInteractor(Interactor<I, R> interactor, I input,
                                           SubscriberFactory<?, R> factory) {

        Observable<R> interactorObservable = interactor.createObservable(input);

        launchInteractor(interactorObservable, factory);
    }

    protected <R> void launchInteractor(Observable<R> interactorObservable,
                                        SubscriberFactory<?, R> factory) {

        @SuppressWarnings("unchecked")
        SubscriberFactory<BasePresenter<V, L>, R> factory1 =
                (SubscriberFactory<BasePresenter<V, L>, R>) factory;

        AsyncSubject<R> asyncSubject = AsyncSubject.create();
        interactorObservable.subscribe(asyncSubject);

        storeInResponseMemento(asyncSubject, factory1);

        Subscription subscription = asyncSubject
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        nullOutResponseMemento();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        nullOutResponseMemento();
                    }
                })
                .subscribe(factory1.create(this));

        interactorSubscriptions.add(subscription);
    }

    private <R> void storeInResponseMemento(AsyncSubject<R> asyncSubject,
                                            SubscriberFactory<BasePresenter<V, L>, R> factory) {
        InteractorResponseMementoImpl<BasePresenter<V, L>, R> responseMemento =
                new InteractorResponseMementoImpl<>();

        responseMemento.store(asyncSubject, factory);
        this.interactorResponseMemento = responseMemento;
    }

    private void nullOutResponseMemento() {
        if (interactorResponseMemento != null) {
            interactorResponseMemento.clear();
        }
    }

    protected void retainResponseMemento() {

        ActivityView activity = getActivityView();

        if (isInteractorResponseMementoNotEmpty() && activity.isChangingConfigurations()) {
            activity.storeInRetainedFragment(getMementoKey(), interactorResponseMemento);
        }
    }

    abstract protected ActivityView getActivityView();

    private String getMementoKey() {
        return getClass().getName();
    }

    protected void checkMementoForPendingInteractorResponse() {
        if (isInteractorResponseMementoNotEmpty()) {
            processInteractorResponseAfterPause();
        } else {
            checkInteractorResponseAfterConfigurationChange();
        }
    }

    private void processInteractorResponseAfterPause() {
        Subscription subscription = interactorResponseMemento.handleResonse(this);
        interactorSubscriptions.add(subscription);
    }

    private void checkInteractorResponseAfterConfigurationChange() {
        ActivityView activity = getActivityView();

        @SuppressWarnings("unchecked")
        InteractorResponseMemento<BasePresenter<V, L>, ?> responseMemento =
                activity.retrieveFromRetainedFragment(InteractorResponseMemento.class, getMementoKey());

        if (responseMemento != null) {
            responseMemento.handleResonse(this);
        }
    }
}