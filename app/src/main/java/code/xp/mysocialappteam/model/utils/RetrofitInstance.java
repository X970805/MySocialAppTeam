package code.xp.mysocialappteam.model.utils;

import android.content.Context;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import code.xp.mysocialappteam.control.ApiInterface;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 徐宏福 on 2017/12/15.
 */

public class RetrofitInstance {
    private Retrofit mRetrofit = null;
    private OkHttpClient mOkHttpClient = null;
    private ApiInterface mApiInterface = null;
    private static Context context;

    private RetrofitInstance(Context context) {
        this.context = context;
        initOkHttp();

        initRetrofit();
        mApiInterface = mRetrofit.create(ApiInterface.class);
    }

    private void initOkHttp() {
        //设置拦截器，打印出每次的请求结果,在初始化okhttp时 addInterceptor
        //带上token则 addNetworkInterceptor
        //https://drakeet.me/retrofit-2-0-okhttp-3-0-config

        //  HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //   loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Cookie的持久化管理，为每次请求带上cookie
        //http://blog.csdn.net/lyhhj/article/details/51345386
        CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                //  Hawk.put("cookie", cookies.get(cookies.size() - 1));
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());

                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        mOkHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(XiQueApplication.isDebug ? loggingInterceptor : null)
                //  .addInterceptor(loggingInterceptor)
                .cookieJar(mCookieJar)
                //  .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //   .baseUrl(ApiInterface.HOST)
                .build();
    }

    private static class SingletonHolder {
        private static final RetrofitInstance INSTANCE = new RetrofitInstance(context);
    }

    //获取retrofit单例
    public static RetrofitInstance getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //获取apiInterface单例
    public static ApiInterface getApiInterface() {
        return getInstance().mApiInterface;
    }

    public static void disposeFailureInfo(Throwable t) {
        if (t.toString().contains("GaiException")
                || t.toString().contains("SocketTimeoutException")
                || t.toString().contains("UnknownHostException")) {
            Toast.makeText(context, "网络不给力", Toast.LENGTH_SHORT).show();
         //   ToastUtil.showLong("网络不给力");
        } else if (t.toString().contains("ConnectException")) {
          //  ToastUtil.showLong("网络连接失败");
            Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();

        } else if (t.getMessage().equals("未登录")) {
//            UnLoginDispose.startLoginActivity();
//            ToastUtil.show("未登录");
            Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();

        } else {
         //   ToastUtil.show("其它错误");
            Toast.makeText(context, "其它错误", Toast.LENGTH_SHORT).show();

        }

    }
}
