package com.maxblumental.cleanboilerplate.model;

import java.util.List;

public class ConvertedMoney {

    String date;

    String base;

    double amount;

    List<Money> amountsInOtherCurrencies;

    public ConvertedMoney(String date, String base, double amount, List<Money> amountsInOtherCurrencies) {
        this.date = date;
        this.base = base;
        this.amount = amount;
        this.amountsInOtherCurrencies = amountsInOtherCurrencies;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Money> getAmountsInOtherCurrencies() {
        return amountsInOtherCurrencies;
    }

    public void setAmountsInOtherCurrencies(List<Money> amountsInOtherCurrencies) {
        this.amountsInOtherCurrencies = amountsInOtherCurrencies;
    }
}
