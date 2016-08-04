package com.blumental.maxim.cleanmvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import rx.Observable;
import rx.subjects.PublishSubject;

import static com.blumental.maxim.cleanmvp.view.ActivityLifecycleEvents.MENU_CREATED;

abstract public class BaseActivity extends AppCompatActivity implements ActivityView {

    public static final String ACTIVITY_ARGS_KEY = "activity args key";

    private PublishSubject<ActivityLifecycleEvents> lifecycleSubject = PublishSubject.create();

    @Override
    public <R> void switchToActivity(Class<R> activityClass) {

        Intent intent = new Intent(this, activityClass);

        startActivity(intent);
    }

    @Override
    public <R> void switchToActivity(Class<R> activityClass, Bundle args) {

        Intent intent = new Intent(this, activityClass);

        intent.putExtra(ACTIVITY_ARGS_KEY, args);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        lifecycleSubject.onNext(MENU_CREATED);
        return true;
    }

    @Override
    public Observable<ActivityLifecycleEvents> getLifecycleEvents() {
        return lifecycleSubject;
    }
}
