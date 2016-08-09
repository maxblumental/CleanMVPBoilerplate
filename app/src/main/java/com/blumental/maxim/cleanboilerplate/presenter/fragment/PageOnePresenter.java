package com.blumental.maxim.cleanboilerplate.presenter.fragment;

import com.blumental.maxim.cleanboilerplate.view.activity.TabsView;
import com.blumental.maxim.cleanboilerplate.view.fragment.PageOneView;
import com.blumental.maxim.cleanmvp.presenter.fragment.BaseFragmentPresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class PageOnePresenter extends BaseFragmentPresenter<PageOneView, TabsView> {

    @Override
    protected void onActivityMenuCreated() {
        TabsView tabsView = getActivity();

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
