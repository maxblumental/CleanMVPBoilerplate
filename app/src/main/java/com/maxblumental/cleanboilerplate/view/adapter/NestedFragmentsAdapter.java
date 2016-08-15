package com.maxblumental.cleanboilerplate.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxblumental.cleanboilerplate.view.fragment.nested.FirstNestedFragment;
import com.maxblumental.cleanboilerplate.view.fragment.nested.SecondNestedFragment;

public class NestedFragmentsAdapter extends FragmentPagerAdapter {

    private static final int TABS_NUMBER = 2;

    public NestedFragmentsAdapter(FragmentManager fm) {
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
                return "Fragment one";
            case 1:
                return "Fragment two";

        }

        throw new IndexOutOfBoundsException("Invalid tab index!");
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FirstNestedFragment();
            case 1:
                return new SecondNestedFragment();

        }

        throw new IndexOutOfBoundsException("Invalid nested fragment index!");
    }
}
