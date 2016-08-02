package com.blumental.maxim.cleanmvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blumental.maxim.cleanmvp.view.FragmentView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;
import rx.subscriptions.CompositeSubscription;

abstract public class BaseFragmentPresenter<T extends FragmentView> implements FragmentPresenter<T> {

    protected T view;

    private CompositeSubscription viewSubscriptions;

    private CompositeSubscription interactorSubscriptions;

    private CompositeSubscription asyncSubjectSubscriptions;

    private Memento<?> memento;

    @Override
    public void onAttach(T view) {
        this.view = view;
        asyncSubjectSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

        viewSubscriptions = new CompositeSubscription();

        interactorSubscriptions = new CompositeSubscription();

        subscribeOnViewEvents();

        if (memento != null && memento.hasElement()) {
            Subscription subscription = memento.resubscribe();
            interactorSubscriptions.add(subscription);
        }
    }

    protected void subscribeOnViewEvents() {

    }

    protected void addViewSubscription(Subscription subscription) {
        viewSubscriptions.add(subscription);
    }

    @Override
    public void onPause() {

        viewSubscriptions.unsubscribe();

        interactorSubscriptions.unsubscribe();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {
        asyncSubjectSubscriptions.unsubscribe();
    }

    protected <R> void observeInteractor(Observable<R> interactorObservable,
                                         Subscriber<R> subscriber) {

        AsyncSubject<R> asyncSubject = AsyncSubject.create();

        Subscription subscription = interactorObservable.subscribe(asyncSubject);

        asyncSubjectSubscriptions.add(subscription);

        MementoImpl<R> memento = new MementoImpl<>();
        memento.store(asyncSubject, subscriber);
        this.memento = memento;

        interactorSubscriptions.add(
                asyncSubject
                        .doOnCompleted(nullOutMemento())
                        .subscribe(subscriber)
        );
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
