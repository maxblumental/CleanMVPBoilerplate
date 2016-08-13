package com.blumental.maxim.cleanboilerplate.view.fragment.nested;

import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

public interface NestedFragmentsContainerView extends FragmentView<NestedFragmentsView> {

    void switchToFragmentOne();

    void switchToFragmentTwo();
}
