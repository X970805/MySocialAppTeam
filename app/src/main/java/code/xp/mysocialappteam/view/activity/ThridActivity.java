package code.xp.mysocialappteam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import code.xp.mysocialappteam.R;
//import org.greenrobot.eventbus.EventBus;


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
    //    EventBus.getDefault().register(this);

        int i = Integer.parseInt(Build.VERSION.SDK);

        System.out.println("_______________________"+i);
        if(i<23) {

            MyPresent myPresent = new MyPresent(this);
            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());

            myPresent.setequipment(uuid);
        }
    else{
            initPermission();
            // shouldRequest();
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

//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void getUUID(String s) {
//        System.out.println(s);
//    }

 //   @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
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
            System.out.println("成功------------"+uuid);
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
        System.out.println(s + "____");
       // EventBus.getDefault().postSticky(s);
    }
}
