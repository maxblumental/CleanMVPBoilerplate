package com.blumental.maxim.cleanboilerplate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blumental.maxim.cleanboilerplate.view.fragment.PageOneFragment;
import com.blumental.maxim.cleanboilerplate.view.fragment.PageTwoFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private static final int TABS_NUMBER = 2;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return TABS_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Page one";
            case 1:
                return "Page two";

        }

        throw new IndexOutOfBoundsException("Invalid tab index!");
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PageOneFragment();
            case 1:
                return new PageTwoFragment();

        }

        throw new IndexOutOfBoundsException("Invalid tab index!");
    }
}
