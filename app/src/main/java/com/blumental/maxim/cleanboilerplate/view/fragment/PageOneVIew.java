package com.blumental.maxim.cleanboilerplate.view.fragment;

import com.blumental.maxim.cleanboilerplate.view.activity.TabsView;
import com.blumental.maxim.cleanmvp.view.FragmentView;

public interface PageOneView extends FragmentView<TabsView> {

    String getText();

    void setText(String query);
}
