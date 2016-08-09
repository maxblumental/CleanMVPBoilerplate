package com.blumental.maxim.cleanmvp.presenter.activity;

import com.blumental.maxim.cleanmvp.presenter.Presenter;
import com.blumental.maxim.cleanmvp.view.activity.ActivityView;

public interface ActivityPresenter<V extends ActivityView> extends Presenter<V, ActivityLifecycle> {

}
