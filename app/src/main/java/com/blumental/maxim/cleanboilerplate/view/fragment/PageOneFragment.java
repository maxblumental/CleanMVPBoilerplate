package com.blumental.maxim.cleanboilerplate.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.fragment.PageOnePresenter;
import com.blumental.maxim.cleanboilerplate.view.activity.TabsView;
import com.blumental.maxim.cleanmvp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageOneFragment extends BaseFragment<PageOnePresenter, TabsView> implements PageOneView {

    @BindView(R.id.text)
    TextView textView;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected PageOnePresenter getPresenter() {
        return new PageOnePresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page_one;
    }

    @Override
    protected int getContainerViewId() {
        return -1;
    }

    @Override
    public String getText() {
        return textView.getText().toString();
    }

    @Override
    public void setText(String text) {
        textView.setText(Html.fromHtml(text));
    }
}
