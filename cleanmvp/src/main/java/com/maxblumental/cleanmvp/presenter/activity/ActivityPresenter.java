package com.maxblumental.cleanmvp.presenter.activity;

import com.maxblumental.cleanmvp.presenter.Presenter;
import com.maxblumental.cleanmvp.view.activity.ActivityView;

public interface ActivityPresenter<V extends ActivityView> extends Presenter<V, ActivityLifecycle> {

}
