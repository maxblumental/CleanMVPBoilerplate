package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscription;
import rx.subjects.AsyncSubject;

interface Memento<P extends BaseFragmentPresenter<?, ?>, T> {

    void store(AsyncSubject<T> asyncSubject, SubscriberFactory<P, T> factory);

    boolean hasElement();

    void clear();

    Subscription resubscribe(P presenter);
}