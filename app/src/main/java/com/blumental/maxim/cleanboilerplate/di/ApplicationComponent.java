package com.blumental.maxim.cleanboilerplate.di;


import com.blumental.maxim.cleanboilerplate.executor.ExecutorImpl;
import com.blumental.maxim.cleanboilerplate.repository.RepositoryImpl;
import com.blumental.maxim.cleanboilerplate.repository.fixer_service.FixerServiceFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FixerServiceFactory.class})
public interface ApplicationComponent {

    @Singleton
    ExecutorImpl executor();

    @Singleton
    RepositoryImpl repository();
}
