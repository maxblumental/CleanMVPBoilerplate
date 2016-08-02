package com.blumental.maxim.cleanmvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blumental.maxim.cleanmvp.presenter.FragmentPresenter;

abstract public class BaseFragment<T extends FragmentPresenter<?>> extends Fragment implements FragmentView {

    public final String TAG = getClass().getName();

    private T presenter;

    protected abstract T getInjectedPresenter();

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = getInjectedPresenter();
        ((FragmentPresenter<BaseFragment<T>>) presenter).onAttach(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        presenter.onCreateView(savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();
    }

    @Override
    public <R> void switchToFragment(Class<R> fragmentClass, Bundle args, boolean addToBackStack) {
        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        String tag = fragmentClass.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                return;
            }
        }

        fragment.setArguments(args);

        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(getContainerViewId(), fragment, tag);

        if (addToBackStack) {
            transaction = transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    protected abstract int getContainerViewId();
}
