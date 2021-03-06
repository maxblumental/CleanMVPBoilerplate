package com.maxblumental.cleanmvp.presenter.memento;

import android.support.annotation.NonNull;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.presenter.SubscriberFactory;

import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;

public class InteractorResponseMementoImpl<P extends BasePresenter<?, ?>, R> implements InteractorResponseMemento<P, R> {

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
    public boolean isNotEmpty() {
        return hasElement;
    }

    @Override
    public void clear() {
        hasElement = false;

        asyncSubject = null;

        factory = null;
    }

    @Override
    public Subscription handleResonse(P presenter) {
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
