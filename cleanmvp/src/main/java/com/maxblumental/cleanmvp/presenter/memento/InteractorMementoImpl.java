package com.maxblumental.cleanmvp.presenter.memento;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.presenter.SubscriberFactory;

import rx.Observable;

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
    public Observable<R> getInteractorObservable() {
        return interactorObservable;
    }

    @Override
    public SubscriberFactory<P, R> getSubscriberFactory() {
        return factory;
    }
}