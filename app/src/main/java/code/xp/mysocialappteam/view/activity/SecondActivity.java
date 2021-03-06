package code.xp.mysocialappteam.view.activity;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.utils.StatusBarCompat;
import code.xp.mysocialappteam.view.fragment.Yd2Fragment;
import code.xp.mysocialappteam.view.fragment.YdFragment;

public class SecondActivity extends AutoLayoutActivity {
    private List<Fragment> fragments;

    private ViewPager mVp;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        StatusBarCompat.compat(this, Color.BLACK);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        sp.edit().putBoolean("flag", true).commit();
        initView();
        fragments = new ArrayList<>();
        YdFragment ydFragment = new YdFragment();
        Yd2Fragment yd2Fragment = new Yd2Fragment();
        fragments.add(ydFragment);
        fragments.add(yd2Fragment);
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

    }

    private void initView() {
        mVp = findViewById(R.id.vp);
    }

}
