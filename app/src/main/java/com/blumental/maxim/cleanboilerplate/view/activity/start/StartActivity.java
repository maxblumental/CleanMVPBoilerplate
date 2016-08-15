package com.blumental.maxim.cleanboilerplate.view.activity.start;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.activity.StartPresenter;
import com.blumental.maxim.cleanboilerplate.view.adapter.DrawerAdapter;
import com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItemClickListener;
import com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItemDecoration;
import com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItems;
import com.blumental.maxim.cleanmvp.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.view.GravityCompat.START;

public class StartActivity extends BaseActivity<StartPresenter> implements StartView, DrawerItemClickListener {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.drawerItemList)
    RecyclerView drawerItemList;

    private ActionBarDrawerToggle drawerToggle;

    private DrawerItems selectedDrawerItemType;

    @Override
    protected StartPresenter getPresenter() {
        return new StartPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ButterKnife.bind(this);

        drawerItemList.setLayoutManager(new LinearLayoutManager(this));
        drawerItemList.addItemDecoration(new DrawerItemDecoration());
        drawerItemList.setAdapter(new DrawerAdapter(this));

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Demos");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu();
                if (selectedDrawerItemType != null) {
                    selectedDrawerItemType.navigate(StartActivity.this);
                    selectedDrawerItemType = null;
                }
            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onItemClick(DrawerItems itemType) {
        selectedDrawerItemType = itemType;
        drawerLayout.closeDrawer(START);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(START)) {
            drawerLayout.closeDrawer(START);
        } else {
            super.onBackPressed();
        }
    }
}