package com.blumental.maxim.cleanboilerplate.view.activity.error;

import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

import rx.Observable;

public interface ErrorServiceView extends ActivityView {

    void showProgress();

    void hideProgress();

    void showResponse(String response);

    void goToErrorState();

    void goToNormalState();

    Observable<Void> getSendRequestButtonClicks();

    Observable<Void> getRetryButtonClicks();
}
