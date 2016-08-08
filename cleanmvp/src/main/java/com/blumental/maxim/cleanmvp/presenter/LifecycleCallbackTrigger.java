package com.blumental.maxim.cleanmvp.presenter;

public interface LifecycleCallbackTrigger {
    void call(BaseFragmentPresenter<?, ?> presenter);
}