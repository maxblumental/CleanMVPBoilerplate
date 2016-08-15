package com.maxblumental.cleanboilerplate.view.fragment.nested;

import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

import rx.Observable;

public interface SecondNestedView extends FragmentView<NestedFragmentsView> {

    Observable<Void> getGoToFirstFragmentButtonClicks();
}
