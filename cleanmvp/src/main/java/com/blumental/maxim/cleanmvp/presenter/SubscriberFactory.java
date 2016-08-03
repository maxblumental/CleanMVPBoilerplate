package com.blumental.maxim.cleanmvp.presenter;

import rx.Subscriber;

public interface SubscriberFactory<R> {
    Subscriber<R> create();
}
