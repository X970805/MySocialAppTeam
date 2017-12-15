package code.xp.mysocialappteam.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import code.xp.mysocialappteam.R;

public class ThridActivity extends AutoLayoutActivity implements View.OnClickListener {

    private TextView recommend;
    private TextView attention;
    private ViewPager viewPager;
    private View leftView;
    private View rightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        initView();


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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recommend:
                recommend.setTextColor(Color.parseColor("#333333"));
                attention.setTextColor(Color.parseColor("#999999"));
                leftView.setVisibility(View.VISIBLE);
                rightView.setVisibility(View.INVISIBLE);
                break;
            case R.id.attention:
                attention.setTextColor(Color.parseColor("#333333"));
                recommend.setTextColor(Color.parseColor("#999999"));
                leftView.setVisibility(View.INVISIBLE);
                rightView.setVisibility(View.VISIBLE);
                break;
            case R.id.viewPager:

                break;

        }
    }
}
