package com.maxblumental.cleanboilerplate.view.activity.tabs;

import com.maxblumental.cleanmvp.view.activity.ActivityView;

import rx.Observable;

public interface TabsView extends ActivityView {

    Observable<String> getSearchQueryObservable();
}
