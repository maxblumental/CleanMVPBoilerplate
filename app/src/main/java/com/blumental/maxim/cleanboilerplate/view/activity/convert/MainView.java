package com.blumental.maxim.cleanboilerplate.view.activity.convert;

import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

public interface MainView extends ActivityView {
    boolean isActivityStart();

    void switchToInputMoneyScreen();
}
