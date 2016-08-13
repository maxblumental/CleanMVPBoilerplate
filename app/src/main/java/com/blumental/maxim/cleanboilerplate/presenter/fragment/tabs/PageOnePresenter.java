package com.blumental.maxim.cleanboilerplate.presenter.fragment.tabs;

import com.blumental.maxim.cleanboilerplate.view.activity.tabs.TabsView;
import com.blumental.maxim.cleanboilerplate.view.fragment.tabs.PageOneView;
import com.blumental.maxim.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PageOnePresenter extends BaseFragmentPresenter<PageOneView, TabsView> {

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
