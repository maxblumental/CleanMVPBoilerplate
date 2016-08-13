package com.blumental.maxim.cleanboilerplate.view.fragment.nested;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.presenter.fragment.nested.SecondNestedFragmentPresenter;
import com.blumental.maxim.cleanboilerplate.view.activity.nested.NestedFragmentsView;
import com.blumental.maxim.cleanmvp.view.fragment.BaseFragment;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

import static com.jakewharton.rxbinding.view.RxView.clicks;

public class SecondNestedFragment extends BaseFragment<SecondNestedFragmentPresenter, NestedFragmentsView> implements SecondNestedView {

    @BindView(R.id.go_to_fragment_one_button)
    Button goToFragmentOneButton;

    @Override
    protected SecondNestedFragmentPresenter getPresenter() {
        return new SecondNestedFragmentPresenter();
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
        return R.layout.nested_fragment_two;
    }

    @Override
    protected int getContainerViewId() {
        return -1;
    }

    @Override
    public Observable<Void> getGoToFirstFragmentButtonClicks() {
        return clicks(goToFragmentOneButton);
    }

    @Override
    public FragmentView<NestedFragmentsView> getContainerFragmentView() {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        String tag = NestedFragmentsContainerFragment.class.getName();

        return (NestedFragmentsContainerView) manager.findFragmentByTag(tag);
    }
}
