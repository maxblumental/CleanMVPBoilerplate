package com.blumental.maxim.cleanboilerplate.view.activity.convert;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.activity.MainPresenter;
import com.blumental.maxim.cleanboilerplate.view.fragment.convert.InputMoneyFragment;
import com.blumental.maxim.cleanmvp.view.activity.BaseActivity;

import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean isActivityStart() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        return fragments == null || fragments.isEmpty();
    }

    @Override
    public void switchToInputMoneyScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        InputMoneyFragment fragment = new InputMoneyFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }
}
