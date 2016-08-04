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
import com.blumental.maxim.cleanboilerplate.presenter.PageTwoPresenter;
import com.blumental.maxim.cleanboilerplate.view.activity.TabsView;
import com.blumental.maxim.cleanmvp.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PageTwoFragment extends BaseFragment<PageTwoPresenter, TabsView> implements PageTwoView {

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
    protected PageTwoPresenter getPresenter() {
        return new PageTwoPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.page_two;
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
