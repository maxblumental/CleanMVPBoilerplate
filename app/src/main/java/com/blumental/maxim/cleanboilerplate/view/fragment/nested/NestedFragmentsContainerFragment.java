package com.blumental.maxim.cleanboilerplate.view.fragment.nested;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.fragment.nested.NestedFragmentsContainerPresenter;
import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanboilerplate.view.adapter.NestedFragmentsAdapter;
import com.blumental.maxim.cleanmvp.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedFragmentsContainerFragment
        extends BaseFragment<NestedFragmentsContainerPresenter, NestedFragmentsView>
        implements NestedFragmentsContainerView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);

        initializeViewPager();

        return view;
    }

    @Override
    protected NestedFragmentsContainerPresenter getPresenter() {
        return new NestedFragmentsContainerPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.nested_fragments_container;
    }

    @Override
    protected int getContainerViewId() {
        return R.id.mainFrame;
    }

    private void initializeViewPager() {
        NestedFragmentsAdapter adapter =
                new NestedFragmentsAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void switchToFragmentOne() {
        viewPager.setCurrentItem(0, true);
    }

    @Override
    public void switchToFragmentTwo() {
        viewPager.setCurrentItem(1, true);
    }
}
