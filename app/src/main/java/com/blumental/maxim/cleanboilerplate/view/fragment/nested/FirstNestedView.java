package com.blumental.maxim.cleanboilerplate.view.fragment.nested;

import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

import rx.Observable;

public interface FirstNestedView extends FragmentView<NestedFragmentsView> {

    Observable<Void> getGoToSecondFragmentButtonClicks();
}
