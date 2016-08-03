package com.blumental.maxim.cleanboilerplate.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.InputMoneyPresenter;
import com.blumental.maxim.cleanboilerplate.view.adapter.CurrencyAdapter;
import com.blumental.maxim.cleanmvp.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.jakewharton.rxbinding.view.RxView.clicks;

public class InputMoneyFragment extends BaseFragment<InputMoneyPresenter> implements InputMoneyView {

    @BindView(R.id.amountEditText)
    EditText amount;

    @BindView(R.id.convertButton)
    Button convertButton;

    @BindView(R.id.currencySpinner)
    Spinner currencySpinner;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected InputMoneyPresenter getPresenter() {
        return new InputMoneyPresenter();
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        CurrencyAdapter adapter = new CurrencyAdapter(getContext());
        currencySpinner.setAdapter(adapter);

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.input_money_fragment;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }

    @Override
    public String getAmount() {
        return amount.getText().toString();
    }

    @Override
    public String getCurrency() {
        return currencySpinner.getSelectedItem().toString();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void showError(String errorMessage) {
        makeText(getContext(), errorMessage, LENGTH_LONG).show();
    }

    @Override
    public void disableConvertButton() {
        convertButton.setEnabled(false);
    }

    @Override
    public void enableConvertButton() {
        convertButton.setEnabled(true);
    }

    @Override
    public void hideKeyboard() {

        View view = getActivity().getCurrentFocus();

        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public Observable<Void> getConvertButtonClicks() {
        return clicks(convertButton);
    }
}
