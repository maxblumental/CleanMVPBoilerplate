package com.blumental.maxim.cleanboilerplate.presenter;

import android.os.Bundle;

import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.view.ConvertedMoneyView;
import com.blumental.maxim.cleanmvp.presenter.BaseFragmentPresenter;

import java.util.ArrayList;

public class ConvertedMoneyPresenter extends BaseFragmentPresenter<ConvertedMoneyView> {

    public static final String MONEY_LIST_KEY = "money list key";

    public static final String BASE_AMOUNT_KEY = "base amount key";

    public static final String BASE_CURRENCY_KEY = "base currency key";

    @Override
    protected void onActivityCreated() {
        Bundle args = view.getArguments();

        double amount = args.getDouble(BASE_AMOUNT_KEY);
        view.setBaseAmount(Double.toString(amount));

        String currency = args.getString(BASE_CURRENCY_KEY);
        view.setBaseCurrency(currency);

        ArrayList<Money> moneyList = args.getParcelableArrayList(MONEY_LIST_KEY);
        view.showConvertedMoney(moneyList);
    }
}