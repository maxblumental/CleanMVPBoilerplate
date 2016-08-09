package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscriber;

public interface SubscriberFactory<P extends BasePresenter<?, ?>, R> {
    Subscriber<R> create(P presenter);
}
