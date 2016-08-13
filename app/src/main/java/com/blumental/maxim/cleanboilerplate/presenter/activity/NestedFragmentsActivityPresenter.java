package com.blumental.maxim.cleanboilerplate.presenter.activity;

import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanmvp.presenter.activity.BaseActivityPresenter;

public class NestedFragmentsActivityPresenter extends BaseActivityPresenter<NestedFragmentsView> {
    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToNestedFragmentsContainerScreen();
        }
    }
}
