package code.xp.mysocialappteam.view.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import code.xp.mysocialappteam.R;
import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.present.MyPresent;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.fragment.Yd2Fragment;
import code.xp.mysocialappteam.view.fragment.YdFragment;

public class SecondActivity extends AppCompatActivity  {
    private List<Fragment> fragments;

    private ViewPager mVp;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
