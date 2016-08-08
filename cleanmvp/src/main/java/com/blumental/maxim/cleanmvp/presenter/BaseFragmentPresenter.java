package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blumental.maxim.cleanmvp.view.ActivityLifecycleEvents;
import com.blumental.maxim.cleanmvp.view.ActivityView;
import com.blumental.maxim.cleanmvp.view.FragmentView;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subscriptions.CompositeSubscription;

import static com.blumental.maxim.cleanmvp.view.ActivityLifecycleEvents.MENU_CREATED;

abstract public class BaseFragmentPresenter<T extends FragmentView<V>, V extends ActivityView> implements FragmentPresenter<T> {

    private final static String MEMENTO_KEY = "memento key";

    protected T view;

    private CompositeSubscription interactorSubscriptions;

    private CompositeSubscription lifecycleSubscription;

    private Memento<BaseFragmentPresenter<T, V>, ?> memento;

    @Override
    public void setView(T view) {
        this.view = view;
    }

    @Override
    public void observeLifecycle(Observable<LifecycleEvents> observable) {

        lifecycleSubscription = new CompositeSubscription();

        Subscription subscription = observable.subscribe(
                new Action1<LifecycleEvents>() {
                    @Override
                    public void call(LifecycleEvents lifecycleEvents) {
                        lifecycleEvents.call(BaseFragmentPresenter.this);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(getClass().getSimpleName(),
                                "Caught exception while observing view's lifecycle...", throwable);
                    }
                }
        );

        lifecycleSubscription.add(subscription);
    }

    protected void onAttach() {

    }

    protected void onCreate() {

    }

    protected void onCreateView() {

    }

    protected void onActivityCreated() {
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

    protected void onActivityMenuCreated() {

    }

    protected void onStart() {

    }

    protected void onResume() {

        interactorSubscriptions = new CompositeSubscription();

        if (isMementoNotEmpty()) {
            processInteractorResponseAfterPause();
        } else {
            checkInteractorResponseAfterConfigurationChange();
        }
    }

    private boolean isMementoNotEmpty() {
        return memento != null && memento.hasElement();
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

    protected void onPause() {

        interactorSubscriptions.unsubscribe();

        retainMemento();
    }

    private void retainMemento() {
        V activity = getActivity();

        if (isMementoNotEmpty() && activity.isChangingConfigurations()) {
            activity.storeInRetainedFragment(MEMENTO_KEY, memento);
        }
    }

    protected void onStop() {

    }

    protected void onDestroyView() {

    }

    protected void onDestroy() {

    }

    protected void onDetach() {
        lifecycleSubscription.unsubscribe();
    }

    protected <R> void observeInteractor(Observable<R> interactorObservable,
                                         SubscriberFactory<?, R> factory) {

        AsyncSubject<R> asyncSubject = AsyncSubject.create();

        interactorObservable.subscribe(asyncSubject);

        MementoImpl<BaseFragmentPresenter<T, V>, R> memento = new MementoImpl<>();

        @SuppressWarnings("unchecked")
        SubscriberFactory<BaseFragmentPresenter<T, V>, R> factory1 =
                (SubscriberFactory<BaseFragmentPresenter<T, V>, R>) factory;

        memento.store(asyncSubject, factory1);
        this.memento = memento;

        interactorSubscriptions.add(
                asyncSubject
                        .doOnCompleted(nullOutMemento())
                        .subscribe(factory1.create(this))
        );
    }

    @NonNull
    private Action0 nullOutMemento() {
        return new Action0() {
            @Override
            public void call() {
                if (memento != null) {
                    memento.clear();
                }
            }
        };
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