package com.blumental.maxim.cleanboilerplate.view.fragment.convert;

import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

import java.util.List;

public interface ConvertedMoneyView extends FragmentView<ExchangeRatesView> {

    void setBaseAmount(String amount);

    void setBaseCurrency(String currency);

    void showConvertedMoney(List<Money> moneyList);
}
