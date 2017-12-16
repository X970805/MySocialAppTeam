package code.xp.mysocialappteam.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2017/12/15.
 */

public class MyApp extends Application {

    private static Retrofit build;

    @Override
    public void onCreate() {
        super.onCreate();

        getrx();
    }


    @SuppressLint("MissingPermission")
    public static String getUuid(Context context, ContentResolver contentResolver) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(contentResolver, android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

    public void getrx() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(5, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        build = new Retrofit.Builder().baseUrl("http://m2.itmayi.net.cn/")
                .client(builder.build()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //
    }

    public static Retrofit myrxtrofit() {
        return build;
    }
}
