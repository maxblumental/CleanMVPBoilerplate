package com.blumental.maxim.cleanmvp.view;

import android.os.Bundle;

public interface FragmentView {

    <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack);

    Bundle getArguments();
}
