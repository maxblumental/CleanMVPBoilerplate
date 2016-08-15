package com.maxblumental.cleanboilerplate.repository;


import com.maxblumental.cleanboilerplate.model.LatestRates;

import rx.Observable;

public interface Repository {

    Observable<LatestRates> getLatestRates(String baseCurrency);
}
