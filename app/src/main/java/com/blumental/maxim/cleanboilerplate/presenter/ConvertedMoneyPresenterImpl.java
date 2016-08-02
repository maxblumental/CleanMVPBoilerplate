package com.blumental.maxim.cleanboilerplate.presenter;

import android.os.Bundle;

import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.view.ConvertedMoneyView;
import com.blumental.maxim.cleanmvp.presenter.BaseFragmentPresenter;

import java.util.ArrayList;

public class ConvertedMoneyPresenterImpl extends BaseFragmentPresenter<ConvertedMoneyView> implements ConvertedMoneyPresenter {

    @Override
    public void onCreateView(Bundle args) {

        double amount = args.getDouble(BASE_AMOUNT_KEY);
        view.setBaseAmount(Double.toString(amount));

        String currency = args.getString(BASE_CURRENCY_KEY);
        view.setBaseCurrency(currency);

        ArrayList<Money> moneyList = args.getParcelableArrayList(MONEY_LIST_KEY);
        view.showConvertedMoney(moneyList);
    }
}
