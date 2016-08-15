package com.maxblumental.cleanmvp.view.fragment;

import android.os.Bundle;

import com.maxblumental.cleanmvp.view.activity.ActivityView;

public interface FragmentView<V extends ActivityView> {

    <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack);

    Bundle getArguments();

    V getActivityView();

    FragmentView<V> getContainerFragmentView();
}
