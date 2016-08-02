package com.blumental.maxim.cleanmvp.interactor;

import rx.Observable;

public interface Interactor<T, R> {

    Observable<R> run(T argument);
}
