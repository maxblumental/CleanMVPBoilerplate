package com.blumental.maxim.cleanboilerplate.view.activity.nested;

import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

public interface NestedFragmentsView extends ActivityView {
    boolean isActivityStart();

    void switchToNestedFragmentsContainerScreen();
}
