package com.blumental.maxim.cleanboilerplate.executor;

import rx.Observable;

public interface Executor {
    <T> Observable<T> makeAsynchronous(Observable<T> observable);
}
