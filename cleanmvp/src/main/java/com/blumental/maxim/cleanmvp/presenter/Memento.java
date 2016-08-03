package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscription;
import rx.subjects.AsyncSubject;

interface Memento<T> {

    void store(AsyncSubject<T> asyncSubject, SubscriberFactory<T> factory);

    boolean hasElement();

    void clear();

    Subscription resubscribe();
}