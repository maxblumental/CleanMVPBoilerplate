package com.blumental.maxim.cleanmvp.presenter.fragment;

import com.blumental.maxim.cleanmvp.presenter.BasePresenter;
import com.blumental.maxim.cleanmvp.presenter.Memento;
import com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents;
import com.blumental.maxim.cleanmvp.view.activity.ActivityView;
import com.blumental.maxim.cleanmvp.view.fragment.FragmentView;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.blumental.maxim.cleanmvp.presenter.activity.ActivityLifecycleEvents.MENU_CREATED;

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
        V activity = getActivity();

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

        if (isMementoNotEmpty()) {
            processInteractorResponseAfterPause();
        } else {
            checkInteractorResponseAfterConfigurationChange();
        }
    }

    private void processInteractorResponseAfterPause() {
        Subscription subscription = memento.resubscribe(this);
        interactorSubscriptions.add(subscription);
    }

    private void checkInteractorResponseAfterConfigurationChange() {
        V activity = getActivity();

        @SuppressWarnings("unchecked")
        Memento<BaseFragmentPresenter<T, V>, ?> memento =
                activity.retrieveFromRetainedFragment(Memento.class, MEMENTO_KEY);

        if (memento != null) {
            memento.resubscribe(this);
        }
    }

    protected void onActivityMenuCreated() {

    }

    @Override
    public void onPause() {

        interactorSubscriptions.unsubscribe();

        retainMemento();
    }

    private void retainMemento() {
        V activity = getActivity();

        if (isMementoNotEmpty() && activity.isChangingConfigurations()) {
            activity.storeInRetainedFragment(MEMENTO_KEY, memento);
        }
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

    protected V getActivity() {
        return view.getActivityView();
    }

    protected <R> void switchToActivity(Class<R> activityClass) {
        V activity = getActivity();

        if (activity != null) {
            activity.switchToActivity(activityClass);
        }
    }
}