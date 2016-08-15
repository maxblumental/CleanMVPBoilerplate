package com.maxblumental.cleanboilerplate.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Money implements Parcelable {

    double amount;

    String currency;

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(Parcel in) {
        amount = in.readDouble();
        currency = in.readString();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(amount);
        parcel.writeString(currency);
    }
}
