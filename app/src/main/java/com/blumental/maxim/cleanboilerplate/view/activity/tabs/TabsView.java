package com.blumental.maxim.cleanboilerplate.view.activity.tabs;

import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

import rx.Observable;

public interface TabsView extends ActivityView {

    Observable<String> getSearchQueryObservable();
}
