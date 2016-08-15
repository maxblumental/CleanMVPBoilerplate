package com.maxblumental.cleanmvp.presenter.activity;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.view.activity.ActivityView;

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

    protected V getActivityView() {
        return view;
    }
}
