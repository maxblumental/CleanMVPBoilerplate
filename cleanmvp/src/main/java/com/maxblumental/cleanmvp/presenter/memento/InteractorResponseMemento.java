package com.maxblumental.cleanmvp.presenter.memento;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.presenter.SubscriberFactory;

import rx.Subscription;
import rx.subjects.AsyncSubject;

public interface InteractorResponseMemento<P extends BasePresenter<?, ?>, R> {

    void store(AsyncSubject<R> asyncSubject, SubscriberFactory<P, R> factory);

    boolean isNotEmpty();

    void clear();

    Subscription handleResonse(P presenter);
}