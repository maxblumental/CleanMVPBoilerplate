package com.maxblumental.cleanboilerplate.view.fragment.tabs;

import com.maxblumental.cleanboilerplate.view.activity.tabs.TabsView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

public interface PageTwoView extends FragmentView<TabsView> {

    String getText();

    void setText(String text);
}
