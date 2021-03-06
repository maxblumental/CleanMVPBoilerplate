package com.maxblumental.cleanboilerplate.view.fragment.convert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxblumental.cleanboilerplate.R;
import com.maxblumental.cleanboilerplate.model.Money;
import com.maxblumental.cleanboilerplate.presenter.fragment.convert.ConvertedMoneyPresenter;
import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanboilerplate.view.adapter.ConvertedMoneyAdapter;
import com.maxblumental.cleanmvp.view.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConvertedMoneyFragment extends BaseFragment<ConvertedMoneyPresenter, ExchangeRatesView> implements ConvertedMoneyView {

    @BindView(R.id.amountTextView)
    TextView amount;

    @BindView(R.id.currencyTextView)
    TextView currency;

    @BindView(R.id.moneyList)
    RecyclerView moneyList;

    @Override
    protected ConvertedMoneyPresenter getPresenter() {
        return new ConvertedMoneyPresenter();
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);

        moneyList.setLayoutManager(new LinearLayoutManager(getContext()));
        moneyList.setAdapter(new ConvertedMoneyAdapter());

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.converted_money_fragment;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }

    @Override
    public void setBaseAmount(String amount) {
        this.amount.setText(amount);
    }

    @Override
    public void setBaseCurrency(String currency) {
        this.currency.setText(currency);
    }

    @Override
    public void showConvertedMoney(List<Money> moneyList) {
        ConvertedMoneyAdapter adapter = (ConvertedMoneyAdapter) this.moneyList.getAdapter();
        if (adapter != null) {
            adapter.setConvertedMoney(moneyList);
        }
    }
}
