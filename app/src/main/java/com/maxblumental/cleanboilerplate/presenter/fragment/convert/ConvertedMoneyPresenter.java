package com.maxblumental.cleanboilerplate.presenter.fragment.convert;

import android.os.Bundle;

import com.maxblumental.cleanboilerplate.model.Money;
import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanboilerplate.view.fragment.convert.ConvertedMoneyView;
import com.maxblumental.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import java.util.ArrayList;

public class ConvertedMoneyPresenter extends BaseFragmentPresenter<ConvertedMoneyView, ExchangeRatesView> {

    public static final String MONEY_LIST_KEY = "money list key";

    public static final String BASE_AMOUNT_KEY = "base amount key";

    public static final String BASE_CURRENCY_KEY = "base currency key";

    @Override
    public void onActivityCreated() {
        Bundle args = view.getArguments();

        double amount = args.getDouble(BASE_AMOUNT_KEY);
        view.setBaseAmount(Double.toString(amount));

        String currency = args.getString(BASE_CURRENCY_KEY);
        view.setBaseCurrency(currency);

        ArrayList<Money> moneyList = args.getParcelableArrayList(MONEY_LIST_KEY);
        view.showConvertedMoney(moneyList);
    }
}