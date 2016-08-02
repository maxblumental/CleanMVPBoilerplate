package com.blumental.maxim.cleanboilerplate.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blumental.maxim.cleanboilerplate.App;
import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.presenter.ConvertedMoneyPresenter;
import com.blumental.maxim.cleanboilerplate.view.adapter.ConvertedMoneyAdapter;
import com.blumental.maxim.cleanmvp.view.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConvertedMoneyFragment extends BaseFragment<ConvertedMoneyPresenter> implements ConvertedMoneyView {

    @Inject
    ConvertedMoneyPresenter presenter;

    @BindView(R.id.amountTextView)
    TextView amount;

    @BindView(R.id.currencyTextView)
    TextView currency;

    @BindView(R.id.moneyList)
    RecyclerView moneyList;

    @Override
    protected ConvertedMoneyPresenter getInjectedPresenter() {
        App.getComponent().inject(this);
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.converted_money_fragment, container, false);
        ButterKnife.bind(this, view);

        moneyList.setLayoutManager(new LinearLayoutManager(getContext()));
        moneyList.setAdapter(new ConvertedMoneyAdapter());

        Bundle arguments = getArguments();
        presenter.onCreateView(arguments);

        return view;
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
