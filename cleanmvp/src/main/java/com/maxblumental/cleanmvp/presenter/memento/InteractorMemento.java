package com.maxblumental.cleanmvp.presenter.memento;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.presenter.SubscriberFactory;

import rx.Observable;

public interface InteractorMemento<P extends BasePresenter<?, ?>, R> {

    void store(Observable<R> interactorObservable, SubscriberFactory<P, R> factory);

    boolean isNotEmpty();

    Observable<R> getInteractorObservable();

    SubscriberFactory<P, R> getSubscriberFactory();
}