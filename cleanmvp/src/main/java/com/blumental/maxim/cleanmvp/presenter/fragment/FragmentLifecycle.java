package com.blumental.maxim.cleanmvp.presenter.fragment;

import com.blumental.maxim.cleanmvp.presenter.Lifecycle;

public interface FragmentLifecycle extends Lifecycle {

    void onAttach();

    void onCreate();

    void onCreateView();

    void onActivityCreated();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onDetach();
}
