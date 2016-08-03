package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;

class MementoImpl<T> implements Memento<T> {

    private AsyncSubject<T> asyncSubject;

    private SubscriberFactory<T> factory;

    private boolean hasElement;

    @Override
    public void store(AsyncSubject<T> asyncSubject, SubscriberFactory<T> factory) {

        this.asyncSubject = asyncSubject;

        this.factory = factory;

        hasElement = true;
    }

    @Override
    public boolean hasElement() {
        return hasElement;
    }

    @Override
    public void clear() {
        hasElement = false;

        asyncSubject = null;

        factory = null;
    }

    @Override
    public Subscription resubscribe() {
        return asyncSubject
                .doOnCompleted(clearAction())
                .subscribe(factory.create());
    }

    @NonNull
    private Action0 clearAction() {
        return new Action0() {
            @Override
            public void call() {
                clear();
            }
        };
    }


}
