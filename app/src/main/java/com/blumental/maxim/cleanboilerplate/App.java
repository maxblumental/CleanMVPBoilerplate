package com.blumental.maxim.cleanboilerplate;

import android.app.Application;

import com.blumental.maxim.cleanboilerplate.di.ApplicationComponent;
import com.blumental.maxim.cleanboilerplate.di.DaggerApplicationComponent;


public class App extends Application {

    public static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private ApplicationComponent buildComponent() {
        return DaggerApplicationComponent.builder().build();
    }
}
