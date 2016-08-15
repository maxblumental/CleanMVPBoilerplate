package com.maxblumental.cleanboilerplate.presenter.fragment.tabs;

import com.maxblumental.cleanboilerplate.view.activity.tabs.TabsView;
import com.maxblumental.cleanboilerplate.view.fragment.tabs.PageTwoView;
import com.maxblumental.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PageTwoPresenter extends BaseFragmentPresenter<PageTwoView, TabsView> {

    @Override
    protected void onActivityMenuCreated() {
        TabsView tabsView = getActivityView();

        tabsView.getSearchQueryObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        highlightQuery(s);
                    }
                });
    }

    private void highlightQuery(String query) {
        String text = view.getText();

        text = text.replaceAll(query, "<font color='red'>" + query + "</font>");

        view.setText(text);
    }
}
