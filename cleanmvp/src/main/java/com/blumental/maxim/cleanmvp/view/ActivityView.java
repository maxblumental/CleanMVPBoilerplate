package com.blumental.maxim.cleanmvp.view;

import android.os.Bundle;

import rx.Observable;

public interface ActivityView {

    <R> void switchToActivity(Class<R> activityClass);

    <R> void switchToActivity(Class<R> activityClass, Bundle args);

    Observable<ActivityLifecycleEvents> getLifecycleEvents();

    void storeInRetainedFragment(String key, Object object);

    <R> R retrieveFromRetainedFragment(Class<R> objectClass, String key);

    boolean isChangingConfigurations();
}
