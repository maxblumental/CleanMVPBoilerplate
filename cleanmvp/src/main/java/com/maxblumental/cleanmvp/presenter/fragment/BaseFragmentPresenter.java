package com.maxblumental.cleanmvp.presenter.fragment;

import com.maxblumental.cleanmvp.presenter.BasePresenter;
import com.maxblumental.cleanmvp.presenter.activity.ActivityLifecycleEvents;
import com.maxblumental.cleanmvp.view.activity.ActivityView;
import com.maxblumental.cleanmvp.view.fragment.FragmentView;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.maxblumental.cleanmvp.presenter.activity.ActivityLifecycleEvents.MENU_CREATED;

abstract public class BaseFragmentPresenter<T extends FragmentView<V>, V extends ActivityView>
        extends BasePresenter<T, FragmentLifecycle> implements FragmentPresenter<T>, FragmentLifecycle {

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onActivityCreated() {
        V activity = getActivityView();

        if (activity == null) {
            return;
        }

        Subscription subscription = activity.getLifecycleEvents()
                .subscribe(new Action1<ActivityLifecycleEvents>() {
                    @Override
                    public void call(ActivityLifecycleEvents event) {
                        if (event.equals(MENU_CREATED)) {
                            onActivityMenuCreated();
                        }
                    }
                });

        lifecycleSubscription.add(subscription);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

        interactorSubscriptions = new CompositeSubscription();

        checkInteractorResponseMemento();

        retrieveLastInteractorMemento();
    }

    protected void onActivityMenuCreated() {

    }

    @Override
    public void onPause() {

        interactorSubscriptions.unsubscribe();

        retainMementos();
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
        lifecycleSubscription.unsubscribe();
    }

    protected V getActivityView() {
        return view.getActivityView();
    }
}