package code.xp.mysocialappteam.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2017/12/15.
 */

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {
    }

    public static RetrofitUtils getInstance(){

        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }


    private static Retrofit retrofit;

    private static synchronized Retrofit getRetrofit(String url){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.d("xxx", message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder ok = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5000, TimeUnit.SECONDS);

        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public <T> T getApiService(String url,Class<T> cl){
        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(cl);
    }
}