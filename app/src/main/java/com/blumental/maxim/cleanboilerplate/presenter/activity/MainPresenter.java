package com.blumental.maxim.cleanboilerplate.presenter.activity;

import com.blumental.maxim.cleanboilerplate.view.activity.MainView;
import com.blumental.maxim.cleanmvp.presenter.activity.BaseActivityPresenter;

public class MainPresenter extends BaseActivityPresenter<MainView> {

    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToInputMoneyScreen();
        }
    }
}
