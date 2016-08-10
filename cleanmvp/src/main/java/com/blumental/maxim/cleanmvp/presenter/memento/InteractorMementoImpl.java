package com.blumental.maxim.cleanmvp.presenter.memento;

import com.blumental.maxim.cleanmvp.presenter.BasePresenter;
import com.blumental.maxim.cleanmvp.presenter.SubscriberFactory;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class InteractorMementoImpl<P extends BasePresenter<?, ?>, R> implements InteractorMemento<P, R> {

    private Observable<R> interactorObservable;

    private SubscriberFactory<P, R> factory;

    private boolean hasElement;

    @Override
    public void store(Observable<R> interactorObservable, SubscriberFactory<P, R> factory) {

        this.interactorObservable = interactorObservable;

        this.factory = factory;

        hasElement = true;
    }

    @Override
    public boolean isNotEmpty() {
        return hasElement;
    }

    @Override
    public void clear() {
        hasElement = false;

        interactorObservable = null;

        factory = null;
    }

    @Override
    public Subscription replay(P presenter) {
        Subscriber<R> subscriber = factory.create(presenter);
        return interactorObservable.subscribe(subscriber);
    }
}