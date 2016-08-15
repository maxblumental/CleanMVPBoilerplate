package com.maxblumental.cleanboilerplate.view.fragment.convert;

import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

import rx.Observable;

public interface InputMoneyView extends FragmentView<ExchangeRatesView> {

    String getAmount();

    String getCurrency();

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);

    void disableConvertButton();

    void enableConvertButton();

    void hideKeyboard();

    Observable<Void> getConvertButtonClicks();
}
