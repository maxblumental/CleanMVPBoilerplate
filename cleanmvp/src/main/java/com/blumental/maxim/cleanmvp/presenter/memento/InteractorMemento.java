package com.blumental.maxim.cleanmvp.presenter.memento;

import com.blumental.maxim.cleanmvp.presenter.BasePresenter;
import com.blumental.maxim.cleanmvp.presenter.SubscriberFactory;

import rx.Observable;
import rx.Subscription;

public interface InteractorMemento<P extends BasePresenter<?, ?>, R> {

    void store(Observable<R> interactorObservable, SubscriberFactory<P, R> factory);

    boolean isNotEmpty();

    void clear();

    Subscription replay(P presenter);
}