package com.maxblumental.cleanmvp.presenter.activity;

import com.maxblumental.cleanmvp.presenter.Lifecycle;

public interface ActivityLifecycle extends Lifecycle {

    void onCreate();

    void onStart();

    void onResume();

    void onMenuCreated();

    void onPause();

    void onStop();

    void onDestroy();
}
