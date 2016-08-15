package com.maxblumental.cleanboilerplate.interactor;

import com.maxblumental.cleanboilerplate.App;
import com.maxblumental.cleanboilerplate.di.ApplicationComponent;
import com.maxblumental.cleanboilerplate.executor.Executor;
import com.maxblumental.cleanboilerplate.mapper.LatesRatesToConvertedMoneyMapper;
import com.maxblumental.cleanboilerplate.model.ConvertedMoney;
import com.maxblumental.cleanboilerplate.model.Money;
import com.maxblumental.cleanboilerplate.repository.Repository;
import com.maxblumental.cleanmvp.interactor.Interactor;

import javax.inject.Inject;

import rx.Observable;

public class ConvertToAllCurrenciesInteractor implements Interactor<Money, ConvertedMoney> {

    private Executor executor;

    private Repository repository;

    @Inject
    ConvertToAllCurrenciesInteractor(Executor executor, Repository repository) {

        this.executor = executor;

        this.repository = repository;
    }

    public static ConvertToAllCurrenciesInteractor create() {

        ApplicationComponent component = App.getComponent();

        Executor executor = component.executor();
        Repository repository = component.repository();

        return new ConvertToAllCurrenciesInteractor(executor, repository);
    }

    @Override
    public Observable<ConvertedMoney> createObservable(final Money argument) {

        Observable<ConvertedMoney> observable =
                repository
                        .getLatestRates(argument.getCurrency())
                        .map(LatesRatesToConvertedMoneyMapper.map(argument.getAmount()));

        return executor.makeAsynchronous(observable);
    }
}
