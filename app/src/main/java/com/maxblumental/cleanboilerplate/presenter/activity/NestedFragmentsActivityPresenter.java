package com.maxblumental.cleanboilerplate.presenter.activity;

import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.maxblumental.cleanmvp.presenter.activity.BaseActivityPresenter;

public class NestedFragmentsActivityPresenter extends BaseActivityPresenter<NestedFragmentsView> {
    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToNestedFragmentsContainerScreen();
        }
    }
}
