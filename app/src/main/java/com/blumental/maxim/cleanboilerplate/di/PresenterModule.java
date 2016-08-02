package com.blumental.maxim.cleanboilerplate.di;

import com.blumental.maxim.cleanboilerplate.presenter.ConvertedMoneyPresenter;
import com.blumental.maxim.cleanboilerplate.presenter.ConvertedMoneyPresenterImpl;
import com.blumental.maxim.cleanboilerplate.presenter.InputMoneyPresenter;
import com.blumental.maxim.cleanboilerplate.presenter.InputMoneyPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    ConvertedMoneyPresenter getConvertedMoneyPresenter() {
        return new ConvertedMoneyPresenterImpl();
    }

    @Provides
    @Singleton
    InputMoneyPresenter getInputMoneyPresenter() {
        return new InputMoneyPresenterImpl();
    }
}
