package com.blumental.maxim.cleanboilerplate.executor;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExecutorImpl implements Executor {

    @Inject
    public ExecutorImpl() {
    }

    @Override
    public <T> Observable<T> makeAsynchronous(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
