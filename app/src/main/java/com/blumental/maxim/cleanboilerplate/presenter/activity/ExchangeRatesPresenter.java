package com.blumental.maxim.cleanboilerplate.presenter.activity;

import com.blumental.maxim.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.blumental.maxim.cleanmvp.presenter.activity.BaseActivityPresenter;

public class ExchangeRatesPresenter extends BaseActivityPresenter<ExchangeRatesView> {

    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToInputMoneyScreen();
        }
    }
}
