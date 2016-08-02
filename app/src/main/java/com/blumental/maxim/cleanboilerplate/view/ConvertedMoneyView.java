package com.blumental.maxim.cleanboilerplate.view;

import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanmvp.view.FragmentView;

import java.util.List;

public interface ConvertedMoneyView extends FragmentView {

    void setBaseAmount(String amount);

    void setBaseCurrency(String currency);

    void showConvertedMoney(List<Money> moneyList);
}
