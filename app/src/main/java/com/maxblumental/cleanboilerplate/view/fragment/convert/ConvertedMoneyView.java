package com.maxblumental.cleanboilerplate.view.fragment.convert;

import com.maxblumental.cleanboilerplate.model.Money;
import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

import java.util.List;

public interface ConvertedMoneyView extends FragmentView<ExchangeRatesView> {

    void setBaseAmount(String amount);

    void setBaseCurrency(String currency);

    void showConvertedMoney(List<Money> moneyList);
}
