package com.blumental.maxim.cleanmvp.presenter;

import android.os.Bundle;

import com.blumental.maxim.cleanmvp.view.FragmentView;

public interface FragmentPresenter<T extends FragmentView> {

    void onAttach(T view);

    void onCreate(Bundle savedInstanceState);

    void onCreateView(Bundle savedInstanceState);

    void onActivityCreated(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onDetach();
}
