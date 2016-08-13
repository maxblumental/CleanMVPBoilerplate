package com.blumental.maxim.cleanboilerplate.presenter.activity;

import com.blumental.maxim.cleanboilerplate.interactor.ErrorServiceInteractor;
import com.blumental.maxim.cleanboilerplate.view.activity.error.ErrorServiceView;
import com.blumental.maxim.cleanmvp.presenter.SubscriberFactory;
import com.blumental.maxim.cleanmvp.presenter.activity.BaseActivityPresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ErrorServicePresenter extends BaseActivityPresenter<ErrorServiceView> {
    @Override
    public void onResume() {
        super.onResume();

        observeClicks(view.getSendRequestButtonClicks(),
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        handleSendRequestButtonClick();
                    }
                });

        observeClicks(view.getRetryButtonClicks(),
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        handleRetry();
                    }
                });
    }

    private void handleRetry() {
        view.showProgress();
        replayLastInteractor();
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private void handleSendRequestButtonClick() {
        view.showProgress();

        ErrorServiceInteractor errorServiceInteractor = ErrorServiceInteractor.create();

        ErrorServiceSubscriberFactory factory = new ErrorServiceSubscriberFactory();

        launchInteractor(errorServiceInteractor, null, factory);
    }

    private static class ErrorServiceSubscriberFactory
            implements SubscriberFactory<ErrorServicePresenter, String> {

        @Override
        public Subscriber<String> create(ErrorServicePresenter presenter) {
            return presenter.createErrorServiceSubscriber();
        }
    }

    private Subscriber<String> createErrorServiceSubscriber() {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
                view.hideProgress();
                view.goToNormalState();
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
                view.goToErrorState();
            }

            @Override
            public void onNext(String s) {
                view.showResponse(s);
            }
        };
    }
}
