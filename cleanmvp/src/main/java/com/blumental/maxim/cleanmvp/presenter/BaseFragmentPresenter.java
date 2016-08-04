package com.blumental.maxim.cleanmvp.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blumental.maxim.cleanmvp.view.ActivityView;
import com.blumental.maxim.cleanmvp.view.FragmentView;
import com.blumental.maxim.cleanmvp.view.LifecycleEvents;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subscriptions.CompositeSubscription;

abstract public class BaseFragmentPresenter<T extends FragmentView<V>, V extends ActivityView> implements FragmentPresenter<T> {

    protected T view;

    private CompositeSubscription interactorSubscriptions;

    private Subscription lifecycleSubscription;

    private Memento<?> memento;

    @Override
    public void setView(T view) {
        this.view = view;
    }

    @Override
    public void obseveLifecycle(Observable<LifecycleEvents> observable) {
        lifecycleSubscription = observable.subscribe(
                new Action1<LifecycleEvents>() {
                    @Override
                    public void call(LifecycleEvents lifecycleEvents) {
                        handleLifecycleEvent(lifecycleEvents);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(getClass().getSimpleName(),
                                "Caught exception while observing view's lifecycle...", throwable);
                    }
                }
        );
    }

    protected void onAttach() {

    }

    protected void onCreate() {

    }

    protected void onCreateView() {

    }

    protected void onActivityCreated() {

    }

    protected void onStart() {

    }

    protected void onResume() {

        interactorSubscriptions = new CompositeSubscription();

        if (memento != null && memento.hasElement()) {
            Subscription subscription = memento.resubscribe();
            interactorSubscriptions.add(subscription);
        }
    }

    protected void onPause() {

        interactorSubscriptions.unsubscribe();
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
                                         SubscriberFactory<R> factory) {

        AsyncSubject<R> asyncSubject = AsyncSubject.create();

        interactorObservable.subscribe(asyncSubject);

        MementoImpl<R> memento = new MementoImpl<>();
        memento.store(asyncSubject, factory);
        this.memento = memento;

        interactorSubscriptions.add(
                asyncSubject
                        .doOnCompleted(nullOutMemento())
                        .subscribe(factory.create())
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

    private void handleLifecycleEvent(LifecycleEvents lifecycleEvents) {
        switch (lifecycleEvents) {
            case ATTACH:
                onAttach();
                break;
            case CREATE:
                onCreate();
                break;
            case CREATE_VIEW:
                onCreateView();
                break;
            case ACTIVITY_CREATED:
                onActivityCreated();
                break;
            case START:
                onStart();
                break;
            case RESUME:
                onResume();
                break;
            case PAUSE:
                onPause();
                break;
            case STOP:
                onStop();
                break;
            case DESTROY_VIEW:
                onDestroyView();
                break;
            case DESTROY:
                onDestroy();
                break;
            case DETACH:
                onDetach();
                break;
            default:
                throw new IllegalStateException("Unknown lifecycle event");
        }
    }

    protected V getActivity() {
        return view.getActivityView();
    }
}
