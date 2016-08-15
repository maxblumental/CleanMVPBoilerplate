package com.maxblumental.cleanboilerplate.view.fragment.nested;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maxblumental.cleanboilerplate.R;
import com.maxblumental.cleanboilerplate.presenter.fragment.nested.FirstNestedFragmentPresenter;
import com.maxblumental.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.maxblumental.cleanmvp.view.fragment.BaseFragment;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static com.jakewharton.rxbinding.view.RxView.clicks;

public class FirstNestedFragment extends BaseFragment<FirstNestedFragmentPresenter, NestedFragmentsView> implements FirstNestedView {

    @BindView(R.id.go_to_fragment_two_button)
    Button goToFragmentTwoButton;

    @Override
    protected FirstNestedFragmentPresenter getPresenter() {
        return new FirstNestedFragmentPresenter();
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.nested_fragment_one;
    }

    @Override
    protected int getContainerViewId() {
        return -1;
    }

    @Override
    public Observable<Void> getGoToSecondFragmentButtonClicks() {
        return clicks(goToFragmentTwoButton);
    }

    @Override
    public FragmentView<NestedFragmentsView> getContainerFragmentView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        String tag = NestedFragmentsContainerFragment.class.getName();

        return (NestedFragmentsContainerView) manager.findFragmentByTag(tag);
    }
}
