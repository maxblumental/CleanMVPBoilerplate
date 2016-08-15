package com.maxblumental.cleanmvp.presenter.fragment;

import com.maxblumental.cleanmvp.presenter.Presenter;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

public interface FragmentPresenter<T extends FragmentView> extends Presenter<T, FragmentLifecycle> {

}