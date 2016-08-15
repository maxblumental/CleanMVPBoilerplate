package com.blumental.maxim.cleanboilerplate.view.activity.convert;

import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

public interface ExchangeRatesView extends ActivityView {
    boolean isActivityStart();

    void switchToInputMoneyScreen();
}
