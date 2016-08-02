package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;

class MementoImpl<T> implements Memento<T> {

    private AsyncSubject<T> asyncSubject;

    private Subscriber<T> subscriber;

    private boolean hasElement;

    @Override
    public void store(AsyncSubject<T> asyncSubject, Subscriber<T> subscriber) {

        this.asyncSubject = asyncSubject;

        this.subscriber = subscriber;

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

        subscriber = null;
    }

    @Override
    public Subscription resubscribe() {
        return asyncSubject
                .doOnCompleted(clearAction())
                .subscribe(subscriber);
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
