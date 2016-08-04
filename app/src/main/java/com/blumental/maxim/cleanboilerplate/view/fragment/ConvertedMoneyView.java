package com.blumental.maxim.cleanboilerplate.view.fragment;

import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.view.activity.MainActivityView;
import com.blumental.maxim.cleanmvp.view.FragmentView;

import java.util.List;

public interface ConvertedMoneyView extends FragmentView<MainActivityView> {

    void setBaseAmount(String amount);

    void setBaseCurrency(String currency);

    void showConvertedMoney(List<Money> moneyList);
}
