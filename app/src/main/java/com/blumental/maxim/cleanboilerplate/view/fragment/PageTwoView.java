package com.blumental.maxim.cleanboilerplate.view.fragment;

import com.blumental.maxim.cleanboilerplate.view.activity.TabsView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

public interface PageTwoView extends FragmentView<TabsView> {

    String getText();

    void setText(String text);
}
