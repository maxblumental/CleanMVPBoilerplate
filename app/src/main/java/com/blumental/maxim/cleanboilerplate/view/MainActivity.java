package com.blumental.maxim.cleanboilerplate.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blumental.maxim.cleanboilerplate.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments == null || fragments.isEmpty()) {
            switchToInputMoneyScreen();
        }
    }

    private void switchToInputMoneyScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        InputMoneyFragment fragment = new InputMoneyFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment, fragment.TAG)
                .commit();
    }
}
