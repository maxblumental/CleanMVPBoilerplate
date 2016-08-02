package com.blumental.maxim.cleanboilerplate.repository.fixer_service;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FixerServiceFactory {

    @Provides
    public static FixerService createService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FixerService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FixerService.class);
    }
}
