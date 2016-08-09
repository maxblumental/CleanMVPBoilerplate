package com.blumental.maxim.cleanmvp.presenter.fragment;

import com.blumental.maxim.cleanmvp.presenter.Presenter;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

public interface FragmentPresenter<T extends FragmentView> extends Presenter<T, FragmentLifecycle> {

}