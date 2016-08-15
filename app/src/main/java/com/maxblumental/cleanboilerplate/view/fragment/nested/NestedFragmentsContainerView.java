package com.maxblumental.cleanboilerplate.view.fragment.nested;

import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

public interface NestedFragmentsContainerView extends FragmentView<NestedFragmentsView> {

    void switchToFragmentOne();

    void switchToFragmentTwo();
}
