package com.blumental.maxim.cleanboilerplate.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.model.Money;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConvertedMoneyAdapter extends RecyclerView.Adapter<ConvertedMoneyAdapter.ViewHolder> {

    private List<Money> convertedMoney;

    public void setConvertedMoney(List<Money> convertedMoney) {
        this.convertedMoney = convertedMoney;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.converted_money_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Money money = convertedMoney.get(position);

        String amountString = String.format("%.2f", money.getAmount());
        String currency = money.getCurrency();

        holder.amount.setText(amountString);
        holder.currency.setText(currency);
    }

    @Override
    public int getItemCount() {
        return convertedMoney == null ? 0 : convertedMoney.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.amount)
        TextView amount;

        @BindView(R.id.currency)
        TextView currency;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
