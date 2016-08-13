package com.blumental.maxim.cleanboilerplate.view.fragment.tabs;

import com.blumental.maxim.cleanboilerplate.view.activity.tabs.TabsView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

public interface PageOneView extends FragmentView<TabsView> {

    String getText();

    void setText(String text);
}
