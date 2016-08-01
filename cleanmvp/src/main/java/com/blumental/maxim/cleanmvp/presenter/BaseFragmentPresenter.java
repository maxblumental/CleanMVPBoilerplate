package com.blumental.maxim.cleanmvp.presenter;

import android.os.Bundle;

import com.blumental.maxim.cleanmvp.view.FragmentView;

abstract public class BaseFragmentPresenter<T extends FragmentView> implements FragmentPresenter<T> {

    //todo think about memory leakage
    private T view;

    @Override
    public void onAttach(T view) {
        this.view = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }
}
