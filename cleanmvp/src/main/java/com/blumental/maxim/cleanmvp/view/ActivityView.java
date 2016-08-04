package com.blumental.maxim.cleanmvp.view;

import android.os.Bundle;

import rx.Observable;

public interface ActivityView {

    <R> void switchToActivity(Class<R> activityClass);

    <R> void switchToActivity(Class<R> activityClass, Bundle args);

    Observable<ActivityLifecycleEvents> getLifecycleEvents();
}
