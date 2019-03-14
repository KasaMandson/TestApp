package com.android.testapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    private static Retrofit retrofit;

    private static synchronized Retrofit getRetrofit(String url) {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T> T getApiService(String url, Class<T> clsName){
        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(clsName);
    }
}
