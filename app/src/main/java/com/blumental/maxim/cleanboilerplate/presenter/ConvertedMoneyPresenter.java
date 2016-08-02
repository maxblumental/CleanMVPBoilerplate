package com.blumental.maxim.cleanboilerplate.presenter;

import com.blumental.maxim.cleanboilerplate.view.ConvertedMoneyView;
import com.blumental.maxim.cleanmvp.presenter.FragmentPresenter;

public interface ConvertedMoneyPresenter extends FragmentPresenter<ConvertedMoneyView> {

    String MONEY_LIST_KEY = "money list key";

    String BASE_AMOUNT_KEY = "base amount key";

    String BASE_CURRENCY_KEY = "base currency key";
}
