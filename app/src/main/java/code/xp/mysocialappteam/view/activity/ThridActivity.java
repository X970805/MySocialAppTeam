package code.xp.mysocialappteam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.present.MyPresent;
import code.xp.mysocialappteam.utils.MyApp;

public class ThridActivity extends AutoLayoutActivity implements OnClickListener {

    private TextView recommend;
    private TextView attention;
    private ViewPager viewPager;
    private View leftView;
    private View rightView;

    private boolean isExit = false;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };
    private TextView recommendMax;
    private TextView attentionMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        initView();


//        viewPager.setAdapter();
    }

    private void initView() {
        recommend = findViewById(R.id.recommend);
        attention = findViewById(R.id.attention);
        viewPager = findViewById(R.id.viewPager);
        leftView = findViewById(R.id.leftView);
        rightView = findViewById(R.id.rightView);
        recommend.setOnClickListener(this);
        attention.setOnClickListener(this);
        viewPager.setOnClickListener(this);
        recommendMax = (TextView) findViewById(R.id.recommendMax);
        recommendMax.setOnClickListener(this);
        attentionMax = (TextView) findViewById(R.id.attentionMax);
        attentionMax.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                leftView.setVisibility(View.VISIBLE);
                rightView.setVisibility(View.INVISIBLE);
                recommendMax.setVisibility(View.VISIBLE);
                recommend.setVisibility(View.GONE);
                attention.setVisibility(View.VISIBLE);
                attentionMax.setVisibility(View.GONE);
                break;
            case R.id.attention:
                leftView.setVisibility(View.INVISIBLE);
                rightView.setVisibility(View.VISIBLE);
                recommendMax.setVisibility(View.GONE);
                recommend.setVisibility(View.VISIBLE);
                attention.setVisibility(View.GONE);
                attentionMax.setVisibility(View.VISIBLE);
                break;
            case R.id.viewPager:

                break;

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

    public void exit(){
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
}
