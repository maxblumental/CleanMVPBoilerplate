package com.maxblumental.cleanboilerplate.di;


import com.maxblumental.cleanboilerplate.executor.ExecutorImpl;
import com.maxblumental.cleanboilerplate.repository.RepositoryImpl;
import com.maxblumental.cleanboilerplate.repository.fixer_service.FixerServiceFactory;

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