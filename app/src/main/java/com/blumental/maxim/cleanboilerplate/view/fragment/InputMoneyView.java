package com.blumental.maxim.cleanboilerplate.view.fragment;

import com.blumental.maxim.cleanboilerplate.view.activity.MainView;
import com.blumental.maxim.cleanmvp.view.FragmentView;

import rx.Observable;

public interface InputMoneyView extends FragmentView<MainView> {

    String getAmount();

    String getCurrency();

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);

    void disableConvertButton();

    void enableConvertButton();

    void hideKeyboard();

    Observable<Void> getConvertButtonClicks();

    Observable<Void> getGoToTabsClicks();
}
