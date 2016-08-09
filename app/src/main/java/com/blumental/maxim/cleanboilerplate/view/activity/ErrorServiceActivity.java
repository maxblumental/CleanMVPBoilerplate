package com.blumental.maxim.cleanboilerplate.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.activity.ErrorServicePresenter;
import com.blumental.maxim.cleanmvp.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.jakewharton.rxbinding.view.RxView.clicks;

public class ErrorServiceActivity extends BaseActivity<ErrorServicePresenter> implements ErrorServiceView {

    @BindView(R.id.sendRequestButton)
    Button sendRequestButton;

    @BindView(R.id.responseTextView)
    TextView responseTextView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.error_view)
    View errorView;

    @Override
    protected ErrorServicePresenter getPresenter() {
        return new ErrorServicePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_service_activity);
        ButterKnife.bind(this);
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
    public void showResponse(String response) {
        responseTextView.setText(response);
    }

    @Override
    public void goToErrorState() {
        errorView.setVisibility(VISIBLE);
    }

    @Override
    public void goToNormalState() {
        errorView.setVisibility(GONE);
    }

    @Override
    public Observable<Void> getSendRequestButtonClicks() {
        return clicks(sendRequestButton);
    }
}
