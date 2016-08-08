package com.blumental.maxim.cleanboilerplate.repository.fixer_service;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FixerServiceFactory {

    @Named("hardcore")
    @Provides
    public static FixerService createRetrofitService() {

        return new HardcoreService();
    }

    @Named("retrofit")
    @Provides
    public static FixerService createHardcoreService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FixerService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FixerService.class);
    }
}
