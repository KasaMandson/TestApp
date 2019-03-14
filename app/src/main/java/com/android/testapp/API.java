package com.android.testapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("weatherApi")
    Call<Weather> getCityWeather(@Query("city") String city);

}
