package com.maxblumental.cleanboilerplate.presenter.activity;

import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanmvp.presenter.activity.BaseActivityPresenter;

public class ExchangeRatesPresenter extends BaseActivityPresenter<ExchangeRatesView> {

    @Override
    public void onResume() {
        super.onResume();

        if (view.isActivityStart()) {
            view.switchToInputMoneyScreen();
        }
    }
}
