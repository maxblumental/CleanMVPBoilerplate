package com.maxblumental.cleanboilerplate.presenter.fragment.nested;

import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.maxblumental.cleanboilerplate.view.fragment.nested.FirstNestedView;
import com.maxblumental.cleanboilerplate.view.fragment.nested.NestedFragmentsContainerView;
import com.maxblumental.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class FirstNestedFragmentPresenter extends BaseFragmentPresenter<FirstNestedView, NestedFragmentsView> {

    @Override
    public void onResume() {
        super.onResume();

        Observable<Void> observable = view.getGoToSecondFragmentButtonClicks();

        observeClicks(observable, new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                handleGoToSecondFragment();
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private void handleGoToSecondFragment() {
        NestedFragmentsContainerView view =
                (NestedFragmentsContainerView) this.view.getContainerFragmentView();

        view.switchToFragmentTwo();
    }
}
