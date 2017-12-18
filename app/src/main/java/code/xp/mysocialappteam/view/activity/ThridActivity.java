package code.xp.mysocialappteam.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

//import org.greenrobot.eventbus.EventBus;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.present.ArticalPresent;
import code.xp.mysocialappteam.present.HotRecommendPresent;
import code.xp.mysocialappteam.present.MyPresent;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;

public class ThridActivity extends AutoLayoutActivity implements MyControl {

    private boolean isExit = false;
    private boolean granted;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);

        int i = Integer.parseInt(Build.VERSION.SDK);
        if(i<23) {
            MyPresent myPresent = new MyPresent(this);
            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
            myPresent.setequipment(uuid);
        } else{
            initPermission();
            getquanxian();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }
    //请求权限
    private void initPermission() {
        int permission = ContextCompat.checkSelfPermission(ThridActivity.this, Manifest.permission.READ_PHONE_STATE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //需不需要解释的dialog
            if (shouldRequest()) return;
            //请求权限
            ActivityCompat.requestPermissions(ThridActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
    }

    private boolean shouldRequest() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
            //显示一个对话框，给用户解释
          //  explainDialog();
            ActivityCompat.requestPermissions(ThridActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            return true;
        }
        return false;
    }



    /**
     * 请求权限的回调
     * <p>
     * 参数1：requestCode-->是requestPermissions()方法传递过来的请求码。
     * 参数2：permissions-->是requestPermissions()方法传递过来的需要申请权限
     * 参数3：grantResults-->是申请权限后，系统返回的结果，PackageManager.PERMISSION_GRANTED表示授权成功，PackageManager.PERMISSION_DENIED表示授权失败。
     * grantResults和permissions是一一对应的
     */
  //添加权限的返回码
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0) {
            //是否授权，可以根据permission作为标记
            granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void getquanxian() {
        if (granted) {
            MyPresent myPresent = new MyPresent(this);
            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
            myPresent.setequipment(uuid);
        } else {
         // Toast.makeText(this, "还没有得到手机的状态权限", Toast.LENGTH_SHORT).show();
            //ActivityCompat.requestPermissions(ThridActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
//            MyPresent myPresent = new MyPresent(this);
//            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
//            System.out.println("手机型号: " + android.os.Build.MODEL + ",\nSDK版本:"
//                    + android.os.Build.VERSION.SDK + ",\n系统版本:"
//                    );
//
//            System.out.println("失败------------"+uuid);
//            myPresent.setequipment(uuid);
        }
    }

    @Override
    public void equipment(String s) {
        //获取到游客ID时进行数据请求
        ArticalPresent articalPresent=new ArticalPresent(this);
        articalPresent.setArticalModel("surfer","index2",s,"1");
        HotRecommendPresent hotRecommendPresent =new HotRecommendPresent(this);
        hotRecommendPresent.setHotRecommendModel("sufer","index",s,"1","5");
    }

    //推荐数据
    @Override
    public void getMyArtical(MyArticleBean articleBean) {
        if (articleBean.getCode()!=200){
            Toast.makeText(this, ""+articleBean.getMsg(), Toast.LENGTH_SHORT).show();
        }else {
            if (articleBean.getData().getArticle()!=null){
                //TODO:适配器
            }
        }
    }

    //热门推荐，在推荐列表第三列
    @Override
    public void getHotRecommend(HotRecommendBean hotRecommendBean) {
        if (hotRecommendBean.getCode()!=200){
            Toast.makeText(this, ""+hotRecommendBean.getMsg(), Toast.LENGTH_SHORT).show();
        }else {
            if (hotRecommendBean.getData().getTopic()!=null){
                //TODO：适配器进行适配  ，在列表第三列
            }
        }
    }


}
