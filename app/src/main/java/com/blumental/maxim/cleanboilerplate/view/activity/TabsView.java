package com.blumental.maxim.cleanboilerplate.view.activity;

import com.blumental.maxim.cleanmvp.view.ActivityView;

import rx.Observable;

public interface TabsView extends ActivityView {

    Observable<String> getSearchQueryObservable();
}
