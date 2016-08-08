package com.blumental.maxim.cleanmvp.presenter;

import com.blumental.maxim.cleanmvp.view.FragmentView;

import rx.Observable;

public interface FragmentPresenter<T extends FragmentView> {

    void setView(T view);

    void observeLifecycle(Observable<LifecycleEvents> observable);
}
