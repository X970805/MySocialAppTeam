package code.xp.mysocialappteam.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import code.xp.mysocialappteam.MainActivity;
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
     //   getpermissions();
    }
    //权限


    @SuppressLint("MissingPermission")
    public static String getUuid(Context context, ContentResolver contentResolver) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
        }

        String deviceId = tm.getDeviceId();
        if (deviceId == null
                || deviceId.trim().length() == 0) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            deviceId = info.getMacAddress();
        }
        return deviceId;
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

    public Object getpermissions(Context context) {
        int permission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_PHONE_STATE);

        if (permission == PackageManager.PERMISSION_GRANTED) {
            //表示已经授权
        }
//PackageManager.PERMISSION_DENIED--->表示权限被否认了

        //   如果在Activity中申请权限可以的调用：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        }

        return permission;
    }
}
