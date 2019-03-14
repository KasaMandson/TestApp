package com.android.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TA-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_get_weather)
    public void getWeather() {
        API api = RetrofitUtils.getInstance().getApiService(Constants.BASE_URL, API.class);

        Call<Weather> call = api.getCityWeather("北京");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather.DataBean weather = response.body().getData();
                Weather.DataBean.YesterdayBean yesterdayBeans = response.body().getData().getYesterday();
                List<Weather.DataBean.ForecastBean> forecastBean = response.body().getData().getForecast();


                Log.i(TAG, "日期: " + yesterdayBeans.getDate());
                Log.i(TAG, "温差: " + yesterdayBeans.getHigh() + ", " + yesterdayBeans.getLow());
                Log.i(TAG, "风向: " + yesterdayBeans.getFx());
                Log.i(TAG, "环境: " + yesterdayBeans.getType());
                Log.i(TAG, "温度: " + weather.getWendu());
                Log.i(TAG, "建议: " + weather.getGanmao());
                Log.i(TAG, "----------------------------------");

                for (Weather.DataBean.ForecastBean future : forecastBean) {
                    Log.i(TAG, "日期: " + future.getDate());
                    Log.i(TAG, "温差: " + future.getHigh() + ", " + future.getLow());
                    Log.i(TAG, "风向: " + future.getFengxiang());
                    Log.i(TAG, "环境: " + future.getType());
                    Log.i(TAG, "----------------------------------");
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }
}
