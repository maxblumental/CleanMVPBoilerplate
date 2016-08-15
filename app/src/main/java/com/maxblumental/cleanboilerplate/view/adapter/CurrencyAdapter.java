package com.maxblumental.cleanboilerplate.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;
import static java.util.Arrays.asList;

public class CurrencyAdapter extends ArrayAdapter<String> {
    public CurrencyAdapter(Context context) {
        super(context, simple_spinner_dropdown_item, createCurrencyList());
    }

    private static List<String> createCurrencyList() {
        return new ArrayList<>(asList(
                "AUD", "BGN", "BRL", "CAD", "CHF", "CNY",
                "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
                "IDR", "ILS", "INR", "JPY", "KRW", "MXN",
                "MYR", "NOK", "NZD", "PHP", "PLN", "RON",
                "RUB", "SEK", "SGD", "THB", "TRY", "USD",
                "ZAR"
        ));
    }
}
