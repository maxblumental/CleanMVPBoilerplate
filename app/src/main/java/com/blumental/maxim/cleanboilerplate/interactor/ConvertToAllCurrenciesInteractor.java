package com.blumental.maxim.cleanboilerplate.interactor;

import com.blumental.maxim.cleanboilerplate.App;
import com.blumental.maxim.cleanboilerplate.di.ApplicationComponent;
import com.blumental.maxim.cleanboilerplate.executor.Executor;
import com.blumental.maxim.cleanboilerplate.mapper.LatesRatesToConvertedMoneyMapper;
import com.blumental.maxim.cleanboilerplate.model.ConvertedMoney;
import com.blumental.maxim.cleanboilerplate.model.Money;
import com.blumental.maxim.cleanboilerplate.repository.Repository;
import com.blumental.maxim.cleanmvp.interactor.Interactor;

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
