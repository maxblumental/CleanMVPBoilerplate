package com.maxblumental.cleanmvp.presenter;

public interface LifecycleCallbackTrigger<L extends Lifecycle> {
    void call(L lifecycle);
}