package com.blumental.maxim.cleanboilerplate.repository.fixer_service;


import com.blumental.maxim.cleanboilerplate.model.LatestRates;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FixerService {

    String BASE_URL = "http://api.fixer.io";

    @GET("/latest")
    Observable<LatestRates> getLatestRates(@Query("base") String baseCurrency);
}
