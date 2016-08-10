package com.blumental.maxim.cleanmvp.presenter.activity;

import com.blumental.maxim.cleanmvp.presenter.BasePresenter;
import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

import rx.subscriptions.CompositeSubscription;

abstract public class BaseActivityPresenter<V extends ActivityView>
        extends BasePresenter<V, ActivityLifecycle> implements ActivityPresenter<V>, ActivityLifecycle {

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        interactorSubscriptions = new CompositeSubscription();

        checkInteractorResponseMemento();

        retrieveLastInteractorMemento();
    }

    @Override
    public void onMenuCreated() {

    }

    @Override
    public void onPause() {
        interactorSubscriptions.unsubscribe();

        retainMementos();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        lifecycleSubscription.unsubscribe();
    }

    @Override
    protected <R> void switchToActivity(Class<R> activityClass) {
        view.switchToActivity(activityClass);
    }

    protected V getActivityView() {
        return view;
    }
}