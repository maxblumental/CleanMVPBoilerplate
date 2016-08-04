package com.blumental.maxim.cleanmvp.view;

import android.os.Bundle;

public interface FragmentView<V extends ActivityView> {

    <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack);

    Bundle getArguments();

    V getActivityView();
}
