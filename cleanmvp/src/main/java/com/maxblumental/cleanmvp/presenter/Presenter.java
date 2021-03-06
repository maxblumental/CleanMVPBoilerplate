package com.maxblumental.cleanmvp.presenter;

import rx.Observable;

public interface Presenter<V, L extends Lifecycle> {

    void setView(V view);

    <T extends LifecycleCallbackTrigger<L>> void observeLifecycle(Observable<T> observable);
}
