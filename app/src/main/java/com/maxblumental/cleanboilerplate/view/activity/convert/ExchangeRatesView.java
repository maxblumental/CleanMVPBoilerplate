package com.maxblumental.cleanboilerplate.view.activity.convert;

import com.maxblumental.cleanmvp.view.activity.ActivityView;

public interface ExchangeRatesView extends ActivityView {
    boolean isActivityStart();

    void switchToInputMoneyScreen();
}
