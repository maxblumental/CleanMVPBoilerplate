package com.blumental.maxim.cleanboilerplate.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.view.adapter.TabsAdapter;
import com.blumental.maxim.cleanmvp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class TabsActivity extends BaseActivity implements TabsView {

    private PublishSubject<String> searchQuerySubject;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_activity);
        ButterKnife.bind(this);

        initializeViewPager();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchQuerySubject = PublishSubject.create();
        searchView.setOnQueryTextListener(createOnQueryTextListener());

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener createOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuerySubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuerySubject.onNext(newText);
                return true;
            }
        };
    }

    private void initializeViewPager() {
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Page 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Page 2"));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public Observable<String> getSearchQueryObservable() {
        return searchQuerySubject;
    }
}
