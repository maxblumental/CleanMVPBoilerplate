package com.blumental.maxim.cleanboilerplate.repository;

import com.blumental.maxim.cleanboilerplate.model.LatestRates;
import com.blumental.maxim.cleanboilerplate.repository.fixer_service.FixerService;

import javax.inject.Inject;

import rx.Observable;

public class RepositoryImpl implements Repository {

    private FixerService service;

    @Inject
    public RepositoryImpl(FixerService service) {
        this.service = service;
    }

    @Override
    public Observable<LatestRates> getLatestRates(String baseCurrency) {
        return service.getLatestRates(baseCurrency);
    }
}
