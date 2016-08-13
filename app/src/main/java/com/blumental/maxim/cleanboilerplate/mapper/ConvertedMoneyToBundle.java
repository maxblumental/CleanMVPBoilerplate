package com.blumental.maxim.cleanboilerplate.mapper;

import android.os.Bundle;

import com.blumental.maxim.cleanboilerplate.model.ConvertedMoney;
import com.blumental.maxim.cleanboilerplate.model.Money;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

import static com.blumental.maxim.cleanboilerplate.presenter.fragment.convert.ConvertedMoneyPresenter.BASE_AMOUNT_KEY;
import static com.blumental.maxim.cleanboilerplate.presenter.fragment.convert.ConvertedMoneyPresenter.BASE_CURRENCY_KEY;
import static com.blumental.maxim.cleanboilerplate.presenter.fragment.convert.ConvertedMoneyPresenter.MONEY_LIST_KEY;

public class ConvertedMoneyToBundle {

    public static Func1<ConvertedMoney, Bundle> map() {
        return new Func1<ConvertedMoney, Bundle>() {
            @Override
            public Bundle call(ConvertedMoney convertedMoney) {
                return map(convertedMoney);
            }
        };
    }

    private static Bundle map(ConvertedMoney convertedMoney) {

        Bundle bundle = new Bundle();

        String baseCurrency = convertedMoney.getBase();
        List<Money> moneyList = convertedMoney.getAmountsInOtherCurrencies();

        filterOutBaseCurrency(baseCurrency, moneyList);

        ArrayList<Money> moneys = new ArrayList<>(moneyList);

        bundle.putParcelableArrayList(MONEY_LIST_KEY, moneys);

        bundle.putDouble(BASE_AMOUNT_KEY, convertedMoney.getAmount());

        bundle.putString(BASE_CURRENCY_KEY, baseCurrency);

        return bundle;
    }

    private static void filterOutBaseCurrency(String baseCurrency, List<Money> moneyList) {
        Money baseMoney = null;

        for (Money money : moneyList) {
            if (money.getCurrency().equals(baseCurrency)) {
                baseMoney = money;
            }
        }

        if (baseMoney != null) {
            moneyList.remove(baseMoney);
        }
    }
}
