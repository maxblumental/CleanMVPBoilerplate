package com.blumental.maxim.cleanboilerplate.view;

import com.blumental.maxim.cleanmvp.view.FragmentView;

public interface InputMoneyView extends FragmentView {

    String getAmount();

    String getCurrency();

    void showProgress();

    void hideProgress();

    void showError(String errorMessage);

    void disableConvertButton();

    void enableConvertButton();

    void hideKeyboard();
}
