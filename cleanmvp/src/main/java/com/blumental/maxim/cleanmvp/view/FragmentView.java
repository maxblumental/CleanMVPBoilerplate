package com.blumental.maxim.cleanmvp.view;

import android.os.Bundle;

public interface FragmentView {

    <T> void switchToFragment(Class<T> fragmnetClass, Bundle args);
}
