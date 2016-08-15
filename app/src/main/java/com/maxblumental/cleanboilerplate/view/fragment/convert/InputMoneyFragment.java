package com.maxblumental.cleanboilerplate.view.fragment.convert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.maxblumental.cleanboilerplate.R;
import com.maxblumental.cleanboilerplate.presenter.fragment.convert.InputMoneyPresenter;
import com.maxblumental.cleanboilerplate.view.activity.convert.ExchangeRatesView;
import com.maxblumental.cleanboilerplate.view.adapter.CurrencyAdapter;
import com.maxblumental.cleanmvp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.jakewharton.rxbinding.view.RxView.clicks;

public class InputMoneyFragment extends BaseFragment<InputMoneyPresenter, ExchangeRatesView> implements InputMoneyView {

    private static final String PROGRESS_KEY = "progress key";

    private static final String CONVERT_BUTTON_ENABLED_KEY = "convert button enabled key";

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (progressBar == null || convertButton == null) {
            outState.putBoolean(PROGRESS_KEY, false);
            outState.putBoolean(CONVERT_BUTTON_ENABLED_KEY, true);
            return;
        }
        outState.putBoolean(PROGRESS_KEY, progressBar.getVisibility() == VISIBLE);
        outState.putBoolean(CONVERT_BUTTON_ENABLED_KEY, convertButton.isEnabled());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        boolean isProgressVisible = savedInstanceState.getBoolean(PROGRESS_KEY);
        progressBar.setVisibility(isProgressVisible ? VISIBLE : GONE);

        boolean convertButtonEnabled = savedInstanceState.getBoolean(CONVERT_BUTTON_ENABLED_KEY);
        convertButton.setEnabled(convertButtonEnabled);
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
        Context context = getContext();

        if (context == null) {
            return;
        }

        makeText(context, errorMessage, LENGTH_LONG).show();
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

        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }

        View view = activity.getCurrentFocus();

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
