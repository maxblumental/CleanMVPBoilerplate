package com.blumental.maxim.cleanboilerplate.presenter;

import com.blumental.maxim.cleanboilerplate.view.InputMoneyView;
import com.blumental.maxim.cleanmvp.presenter.FragmentPresenter;

import rx.Observable;

public interface InputMoneyPresenter extends FragmentPresenter<InputMoneyView> {

    void observeConvertButtonClicks(Observable<Void> observable);
}
