package com.blumental.maxim.cleanboilerplate.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blumental.maxim.cleanboilerplate.interactor.ConvertToAllCurrenciesInteractor;
import com.blumental.maxim.cleanboilerplate.mapper.ConvertedMoneyToBundle;
import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.view.activity.MainView;
import com.blumental.maxim.cleanboilerplate.view.activity.TabsActivity;
import com.blumental.maxim.cleanboilerplate.view.fragment.ConvertedMoneyFragment;
import com.blumental.maxim.cleanboilerplate.view.fragment.InputMoneyView;
import com.blumental.maxim.cleanmvp.presenter.BaseFragmentPresenter;
import com.blumental.maxim.cleanmvp.presenter.SubscriberFactory;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class InputMoneyPresenter extends BaseFragmentPresenter<InputMoneyView, MainView> {

    @Override
    protected void onResume() {
        super.onResume();

        startObservingViewEvents();
    }

    private void startObservingViewEvents() {

        observeClicks(view.getConvertButtonClicks(),
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        handleConvertButtonClick();
                    }
                });

        observeClicks(view.getGoToTabsClicks(),
                new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        handleGoToTabsButtonClick();
                    }
                });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private void handleConvertButtonClick() {

        view.hideKeyboard();
        view.showProgress();
        view.disableConvertButton();

        String amount = view.getAmount();
        String currency = view.getCurrency();

        if (isInputWrong(amount, currency)) {
            onWrongInput();
            return;
        }

        Money money = new Money(Integer.parseInt(amount), currency);

        ConvertToAllCurrenciesInteractor interactor = ConvertToAllCurrenciesInteractor.create();

        Observable<Bundle> interactorObservable = interactor.run(money)
                .map(ConvertedMoneyToBundle.map());

        observeInteractor(interactorObservable,
                new SubscriberFactory<Bundle>() {
                    @Override
                    public Subscriber<Bundle> create() {
                        return createConvertToAllCurrenciesSubscriber();
                    }
                });
    }

    private boolean isInputWrong(String amount, String currency) {
        return amount.isEmpty() || currency.isEmpty();
    }

    private void onWrongInput() {
        view.showError("Enter amount and select a currency!");
        view.hideProgress();
        view.enableConvertButton();
    }

    @NonNull
    private Subscriber<Bundle> createConvertToAllCurrenciesSubscriber() {
        return new Subscriber<Bundle>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
                view.enableConvertButton();
                view.showError(e.getMessage());
            }

            @Override
            public void onNext(Bundle bundle) {
                view.hideProgress();
                view.enableConvertButton();
                view.switchToFragment(ConvertedMoneyFragment.class, bundle, true);
            }
        };
    }

    private void handleGoToTabsButtonClick() {
        switchToActivity(TabsActivity.class);
    }
}
