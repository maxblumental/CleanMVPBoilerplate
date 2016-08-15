package com.maxblumental.cleanboilerplate.view.activity.nested;

import com.maxblumental.cleanmvp.view.activity.ActivityView;

public interface NestedFragmentsView extends ActivityView {
    boolean isActivityStart();

    void switchToNestedFragmentsContainerScreen();
}
