package com.maxblumental.cleanboilerplate.mapper;

import com.maxblumental.cleanboilerplate.model.ConvertedMoney;
import com.maxblumental.cleanboilerplate.model.LatestRates;
import com.maxblumental.cleanboilerplate.model.Money;

import java.util.List;

import rx.functions.Func1;

public class LatesRatesToConvertedMoneyMapper {

    public static Func1<LatestRates, ConvertedMoney> map(final double amount) {
        return new Func1<LatestRates, ConvertedMoney>() {
            @Override
            public ConvertedMoney call(LatestRates latestRates) {
                return LatesRatesToConvertedMoneyMapper.map(latestRates, amount);
            }
        };
    }

    private static ConvertedMoney map(LatestRates latestRates, double amount) {

        List<Money> moneys = latestRates.getRates().toList();
        String base = latestRates.getBase();
        String date = latestRates.getDate();

        for (Money money : moneys) {
            double rate = money.getAmount();
            money.setAmount(rate * amount);
        }

        return new ConvertedMoney(date, base, amount, moneys);
    }
}

