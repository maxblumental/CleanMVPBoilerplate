package com.maxblumental.cleanboilerplate.view.drawer;

import com.maxblumental.cleanboilerplate.R;
import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesActivity;
import com.maxblumental.cleanboilerplate.view.activity.error.ErrorServiceActivity;
import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsActivity;
import com.maxblumental.cleanboilerplate.view.activity.tabs.SearchTabsActivity;
import com.maxblumental.cleanmvp.view.activity.ActivityView;

public enum DrawerItems implements ActivityNavigator {
    EXCHANGE_RATE(R.drawable.ic_euro_symbol_white_48dp, "Exchange rates") {
        @Override
        public void navigate(ActivityView view) {
            view.switchToActivity(ExchangeRatesActivity.class);
        }
    },
    ERROR_SERVICE(R.drawable.ic_error_outline_white_48dp, "Error service") {
        @Override
        public void navigate(ActivityView view) {
            view.switchToActivity(ErrorServiceActivity.class);
        }
    },
    SEARCH_VIEW(R.drawable.ic_search_white_48dp, "Search view") {
        @Override
        public void navigate(ActivityView view) {
            view.switchToActivity(SearchTabsActivity.class);
        }
    },
    TABS(R.drawable.ic_tab_white_48dp, "Tabs") {
        @Override
        public void navigate(ActivityView view) {
            view.switchToActivity(NestedFragmentsActivity.class);
        }
    };

    private final int iconRecourceId;

    private final String title;

    DrawerItems(int iconRecourceId, String title) {

        this.iconRecourceId = iconRecourceId;

        this.title = title;
    }

    public int getIconRecourceId() {
        return iconRecourceId;
    }

    public String getTitle() {
        return title;
    }
}
