package com.blumental.maxim.cleanboilerplate.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.view.fragment.InputMoneyFragment;
import com.blumental.maxim.cleanmvp.view.BaseActivity;

import java.util.List;


public class MainActivity extends BaseActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isActivityStart()) {
            switchToInputMoneyScreen();
        }
    }

    private boolean isActivityStart() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        return fragments == null || fragments.isEmpty();
    }

    private void switchToInputMoneyScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        InputMoneyFragment fragment = new InputMoneyFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }
}
