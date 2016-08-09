package com.blumental.maxim.cleanmvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents;
import com.blumental.maxim.cleanmvp.presenter.activity.ActivityPresenter;
import com.blumental.maxim.cleanmvp.view.RetainedFragment;

import rx.Observable;
import rx.subjects.PublishSubject;

import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.CREATE;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.DESTROY;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.MENU_CREATED;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.PAUSE;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.RESUME;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.START;
import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.STOP;

abstract public class BaseActivity<P extends ActivityPresenter<?>>
        extends AppCompatActivity implements ActivityView {

    public static final String ACTIVITY_ARGS_KEY = "activity args key";

    private static final String RETAINED_FRAGMENT_TAG = "retained fragment tag";

    private PublishSubject<ActivityLifecycleEvents> lifecycleSubject = PublishSubject.create();

    abstract protected P getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lifecycleSubject = PublishSubject.create();

        P presenter = getPresenter();

        @SuppressWarnings("unchecked")
        ActivityPresenter<ActivityView> presenter1 =
                (ActivityPresenter<ActivityView>) presenter;

        presenter1.setView(this);

        presenter1.observeLifecycle(getLifecycleEvents());

        lifecycleSubject.onNext(CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(RESUME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        lifecycleSubject.onNext(MENU_CREATED);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleSubject.onNext(STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(DESTROY);
    }

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
