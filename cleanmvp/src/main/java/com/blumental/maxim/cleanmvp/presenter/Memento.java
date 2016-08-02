package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscriber;
import rx.Subscription;
import rx.subjects.AsyncSubject;

interface Memento<T> {

    void store(AsyncSubject<T> asyncSubject, Subscriber<T> subscriber);

    boolean hasElement();

    void clear();

    Subscription resubscribe();
}