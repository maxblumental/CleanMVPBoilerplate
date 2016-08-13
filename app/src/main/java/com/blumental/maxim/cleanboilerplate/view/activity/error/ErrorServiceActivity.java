package com.blumental.maxim.cleanboilerplate.view.activity.error;

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

    private final static String ERROR_VIEW_VISIBLE_KEY = "error view visible key";

    private final static String PROGRESS_KEY = "progress key";

    private final static String RESPONSE_KEY = "response key";

    @BindView(R.id.sendRequestButton)
    Button sendRequestButton;

    @BindView(R.id.responseTextView)
    TextView responseTextView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.error_view)
    View errorView;

    @BindView(R.id.retry_button)
    Button retryButton;

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ERROR_VIEW_VISIBLE_KEY, errorView.getVisibility() == VISIBLE);
        outState.putBoolean(PROGRESS_KEY, progressBar.getVisibility() == VISIBLE);
        outState.putString(RESPONSE_KEY, responseTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState == null) {
            return;
        }

        boolean isErrorViewVisible = savedInstanceState.getBoolean(ERROR_VIEW_VISIBLE_KEY);
        errorView.setVisibility(isErrorViewVisible ? VISIBLE : GONE);

        boolean isProgressVisible = savedInstanceState.getBoolean(PROGRESS_KEY);
        progressBar.setVisibility(isProgressVisible ? VISIBLE : GONE);

        responseTextView.setText(savedInstanceState.getString(RESPONSE_KEY));
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

    @Override
    public Observable<Void> getRetryButtonClicks() {
        return clicks(retryButton);
    }
}
