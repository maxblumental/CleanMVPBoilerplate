package com.blumental.maxim.cleanboilerplate.repository;


import com.blumental.maxim.cleanboilerplate.model.LatestRates;

import rx.Observable;

public interface Repository {

    Observable<LatestRates> getLatestRates(String baseCurrency);
}
