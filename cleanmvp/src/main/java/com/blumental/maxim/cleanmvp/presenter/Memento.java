package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscription;
import rx.subjects.AsyncSubject;

public interface Memento<P extends BasePresenter<?, ?>, R> {

    void store(AsyncSubject<R> asyncSubject, SubscriberFactory<P, R> factory);

    boolean hasElement();

    void clear();

    Subscription resubscribe(P presenter);
}