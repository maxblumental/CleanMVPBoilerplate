package com.blumental.maxim.cleanboilerplate.view.activity.nested;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.activity.NestedFragmentsActivityPresenter;
import com.blumental.maxim.cleanboilerplate.view.fragment.nested.NestedFragmentsContainerFragment;
import com.blumental.maxim.cleanmvp.view.activity.BaseActivity;

import java.util.List;

public class NestedFragmentsActivity extends BaseActivity<NestedFragmentsActivityPresenter> implements NestedFragmentsView {

    @Override
    protected NestedFragmentsActivityPresenter getPresenter() {
        return new NestedFragmentsActivityPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_fragments_activity);
    }

    @Override
    public boolean isActivityStart() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        return fragments == null || fragments.isEmpty();
    }

    @Override
    public void switchToNestedFragmentsContainerScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        NestedFragmentsContainerFragment fragment = new NestedFragmentsContainerFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }
}