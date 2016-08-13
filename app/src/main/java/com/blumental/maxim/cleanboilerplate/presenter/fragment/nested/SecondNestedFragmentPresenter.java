package com.blumental.maxim.cleanboilerplate.presenter.fragment.nested;

import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanboilerplate.view.fragment.nested.NestedFragmentsContainerView;
import com.blumental.maxim.cleanboilerplate.view.fragment.nested.SecondNestedView;
import com.blumental.maxim.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SecondNestedFragmentPresenter extends BaseFragmentPresenter<SecondNestedView, NestedFragmentsView> {

    @Override
    public void onResume() {
        super.onResume();

        Observable<Void> observable = view.getGoToFirstFragmentButtonClicks();

        observeClicks(observable, new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                handleGoToFirstFragment();
            }
        });
    }

    private void observeClicks(Observable<Void> clicksObservable, Action1<Void> onNextAction) {
        clicksObservable.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction);
    }

    private void handleGoToFirstFragment() {
        NestedFragmentsContainerView view =
                (NestedFragmentsContainerView) this.view.getContainerFragmentView();

        view.switchToFragmentOne();
    }
}
