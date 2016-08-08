package com.blumental.maxim.cleanmvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import rx.Observable;
import rx.subjects.PublishSubject;

import static com.blumental.maxim.cleanmvp.view.ActivityLifecycleEvents.MENU_CREATED;

abstract public class BaseActivity extends AppCompatActivity implements ActivityView {

    public static final String ACTIVITY_ARGS_KEY = "activity args key";

    private static final String RETAINED_FRAGMENT_TAG = "retained fragment tag";

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

    @Override
    public void storeInRetainedFragment(String key, Object object) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        RetainedFragment fragment =
                (RetainedFragment) fragmentManager.findFragmentByTag(RETAINED_FRAGMENT_TAG);

        if (fragment == null) {
            fragment = new RetainedFragment();

            fragmentManager.beginTransaction()
                    .add(fragment, RETAINED_FRAGMENT_TAG)
                    .commit();
        }

        fragment.saveObject(key, object);
    }

    @Override
    public <R> R retrieveFromRetainedFragment(Class<R> objectClass, String key) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        RetainedFragment fragment =
                (RetainedFragment) fragmentManager.findFragmentByTag(RETAINED_FRAGMENT_TAG);

        if (fragment == null) {
            return null;
        }

        return fragment.retrieveObject(objectClass, key);
    }
}
