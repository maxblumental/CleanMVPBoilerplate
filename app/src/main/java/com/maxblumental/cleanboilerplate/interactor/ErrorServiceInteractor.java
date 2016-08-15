package com.maxblumental.cleanboilerplate.interactor;

import com.maxblumental.cleanboilerplate.App;
import com.maxblumental.cleanboilerplate.executor.Executor;
import com.maxblumental.cleanmvp.interactor.Interactor;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class ErrorServiceInteractor implements Interactor<Void, String> {

    private boolean firstLaunch = true;

    private Executor executor;

    @Inject
    public ErrorServiceInteractor(Executor executor) {
        this.executor = executor;
    }

    public static ErrorServiceInteractor create() {
        Executor executor = App.getComponent().executor();

        return new ErrorServiceInteractor(executor);
    }

    @Override
    public Observable<String> createObservable(Void argument) {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException ignored) {
                }

                if (firstLaunch) {
                    firstLaunch = false;
                    subscriber.onError(new IllegalStateException("Something went wrong..."));
                }

                subscriber.onNext("Here's your response");
                subscriber.onCompleted();
            }
        });

        return executor.makeAsynchronous(observable);
    }
}
