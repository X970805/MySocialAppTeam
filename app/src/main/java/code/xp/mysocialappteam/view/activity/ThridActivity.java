package code.xp.mysocialappteam.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.present.MyPresent;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.adapter.RecommendFragmentAdapter;
import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import code.xp.mysocialappteam.view.bean.YKBean;
import code.xp.mysocialappteam.view.fragment.AttentionFragment;
import code.xp.mysocialappteam.view.fragment.RecommendFragment;

//import org.greenrobot.eventbus.EventBus;

public class ThridActivity extends AutoLayoutActivity implements MyControl, View.OnClickListener {

    private boolean isExit = false;
    private boolean granted;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };
    private TextView recommendMax;
    private TextView recommend;
    private TextView attention;
    private TextView attentionMax;
    private View leftView;
    private View rightView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        initView();
        //    EventBus.getDefault().register(this);

        int i = Integer.parseInt(Build.VERSION.SDK);
        if (i < 23) {
            MyPresent myPresent = new MyPresent(this);
            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
            myPresent.setequipment(uuid);
        } else {
            initPermission();
            //shouldRequest();

        }

        AttentionFragment attentionFragment = new AttentionFragment();
        RecommendFragment recommendFragment = new RecommendFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(recommendFragment);
        fragments.add(attentionFragment);
        RecommendFragmentAdapter adapter = new RecommendFragmentAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        leftView.setVisibility(View.VISIBLE);
                        rightView.setVisibility(View.GONE);
                        recommendMax.setVisibility(View.VISIBLE);
                        recommend.setVisibility(View.GONE);
                        attention.setVisibility(View.VISIBLE);
                        attentionMax.setVisibility(View.GONE);
                        break;
                    case 1:
                        leftView.setVisibility(View.INVISIBLE);
                        rightView.setVisibility(View.VISIBLE);
                        recommendMax.setVisibility(View.GONE);
                        recommend.setVisibility(View.VISIBLE);
                        attention.setVisibility(View.GONE);
                        attentionMax.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 点击返回键 ，按两次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
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

            //请求权限
            ActivityCompat.requestPermissions(ThridActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
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
            getquanxian();
        }
    }

    public void getquanxian() {
        if (granted) {
            MyPresent myPresent = new MyPresent(this);
            String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
            System.out.println("成功------------" + uuid);
            myPresent.setequipment(uuid);
        } else {
            ActivityCompat.requestPermissions(ThridActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommendMax:
                viewPager.setCurrentItem(0);
                break;
            case R.id.attention:
                viewPager.setCurrentItem(1);
                break;
            case R.id.recommend:
                viewPager.setCurrentItem(0);
                break;
            case R.id.attentionMax:
                viewPager.setCurrentItem(1);
                break;

        }
    }

    private void initView() {
        recommendMax = (TextView) findViewById(R.id.recommendMax);
        recommend = (TextView) findViewById(R.id.recommend);
        attention = (TextView) findViewById(R.id.attention);
        attentionMax = (TextView) findViewById(R.id.attentionMax);
        leftView = (View) findViewById(R.id.leftView);
        rightView = (View) findViewById(R.id.rightView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        recommendMax.setOnClickListener(this);
        recommend.setOnClickListener(this);
        attention.setOnClickListener(this);
        attentionMax.setOnClickListener(this);
        leftView.setOnClickListener(this);
        rightView.setOnClickListener(this);

    }

    @Override
    public void equipment(YKBean s) {
        if (s.getCode() != 200) {
            Toast.makeText(this, "" + s.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            EventBus.getDefault().postSticky(s.getData().getSurfer_id() + "");
        }
    }
}
