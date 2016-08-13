package com.blumental.maxim.cleanmvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blumental.maxim.cleanmvp.presenter.fragment.FragmentPresenter;
import com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents;
import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

import rx.subjects.PublishSubject;

import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.ACTIVITY_CREATED;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.ATTACH;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.CREATE;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.CREATE_VIEW;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.DESTROY;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.DESTROY_VIEW;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.DETACH;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.PAUSE;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.RESUME;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.START;
import static com.blumental.maxim.cleanmvp.presenter.fragment.FragmentLifecycleEvents.STOP;
import static java.lang.String.format;

abstract public class BaseFragment<T extends FragmentPresenter<?>, V extends ActivityView>
        extends Fragment implements FragmentView<V> {

    public final String TAG = getClass().getName();

    protected abstract T getPresenter();

    private PublishSubject<FragmentLifecycleEvents> lifecycleSubject;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        @SuppressWarnings("unchecked")
        FragmentPresenter<FragmentView> presenter =
                (FragmentPresenter<FragmentView>) getPresenter();

        presenter.setView(this);

        lifecycleSubject = PublishSubject.create();

        presenter.observeLifecycle(lifecycleSubject);
        lifecycleSubject.onNext(ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(CREATE);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(CREATE_VIEW);
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    protected abstract int getLayoutResourceId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lifecycleSubject.onNext(ACTIVITY_CREATED);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext(PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleSubject.onNext(STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        lifecycleSubject.onNext(DESTROY_VIEW);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(DESTROY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifecycleSubject.onNext(DETACH);
    }

    @Override
    public <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack) {
        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        String tag = fragmentClass.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                return;
            }
        }

        fragment.setArguments(args);

        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(getContainerViewId(), fragment, tag);

        if (addToBackStack) {
            transaction = transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    protected abstract int getContainerViewId();

    @Override
    public V getActivityView() {

        try {

            @SuppressWarnings("unchecked")
            V activity = (V) getActivity();

            return activity;

        } catch (ClassCastException exception) {

            String fragmentClass = getClass().getSimpleName();
            String activityClass = getActivity().getClass().getSimpleName();

            String message = format("Wrong FragmentView parametrization! %s is not hosted by %s",
                    fragmentClass, activityClass);

            throw new IllegalStateException(message, exception);
        }
    }

    @Override
    public FragmentView<V> getContainerFragmentView() {
        @SuppressWarnings("unchecked")
        FragmentView<V> parentFragment = (FragmentView<V>) getParentFragment();
        return parentFragment;
    }
}
