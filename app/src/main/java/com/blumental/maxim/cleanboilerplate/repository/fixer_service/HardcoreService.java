package com.blumental.maxim.cleanboilerplate.repository.fixer_service;

import com.blumental.maxim.cleanboilerplate.model.LatestRates;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;

public class HardcoreService implements FixerService {

    private int DELAY = 2_000;

    @Override
    public Observable<LatestRates> getLatestRates(@Query("base") final String baseCurrency) {

        return Observable.create(new Observable.OnSubscribe<LatestRates>() {
            @Override
            public void call(Subscriber<? super LatestRates> subscriber) {
                BufferedReader reader = null;
                try {

                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException ignored) {
                    }

                    URL url = new URL(BASE_URL + "/latest?base=" + baseCurrency);

                    reader = new BufferedReader(new InputStreamReader(url.openStream()));

                    String line;
                    String json = "";

                    while ((line = reader.readLine()) != null) {
                        json += line;
                    }

                    Gson gson = new Gson();
                    LatestRates rates = gson.fromJson(json, LatestRates.class);

                    subscriber.onNext(rates);
                    subscriber.onCompleted();

                } catch (IOException e) {
                    subscriber.onError(e);
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ignored) {
                        }
                    }
                }
            }
        });
    }
}
