package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;

import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;

public class MementoImpl<P extends BasePresenter<?, ?>, R> implements Memento<P, R> {

    private AsyncSubject<R> asyncSubject;

    private SubscriberFactory<P, R> factory;

    private boolean hasElement;

    @Override
    public void store(AsyncSubject<R> asyncSubject, SubscriberFactory<P, R> factory) {

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
    public Subscription resubscribe(P presenter) {
        return asyncSubject
                .doOnCompleted(clearAction())
                .subscribe(factory.create(presenter));
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
