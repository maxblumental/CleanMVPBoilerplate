package com.maxblumental.cleanboilerplate.repository;

import com.maxblumental.cleanboilerplate.model.LatestRates;
import com.maxblumental.cleanboilerplate.repository.fixer_service.FixerService;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

public class RepositoryImpl implements Repository {

    private FixerService service;

    @Inject
    public RepositoryImpl(@Named("hardcore") FixerService service) {
        this.service = service;
    }

    @Override
    public Observable<LatestRates> getLatestRates(String baseCurrency) {
        return service.getLatestRates(baseCurrency);
    }
}
