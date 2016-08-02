package com.blumental.maxim.cleanboilerplate.di;


import com.blumental.maxim.cleanboilerplate.executor.ExecutorImpl;
import com.blumental.maxim.cleanboilerplate.repository.RepositoryImpl;
import com.blumental.maxim.cleanboilerplate.repository.fixer_service.FixerServiceFactory;
import com.blumental.maxim.cleanboilerplate.view.ConvertedMoneyFragment;
import com.blumental.maxim.cleanboilerplate.view.InputMoneyFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, FixerServiceFactory.class})
public interface ApplicationComponent {

    ExecutorImpl executor();

    RepositoryImpl repository();

    void inject(InputMoneyFragment fragment);

    void inject(ConvertedMoneyFragment fragment);
}
