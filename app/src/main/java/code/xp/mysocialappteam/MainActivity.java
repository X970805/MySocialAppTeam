package code.xp.mysocialappteam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import code.xp.mysocialappteam.activity.SecondActivity;
import code.xp.mysocialappteam.fragment.Yd2Fragment;
import code.xp.mysocialappteam.fragment.YdFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private List<Fragment> fragments;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sp = getSharedPreferences("config",MODE_PRIVATE);
        if (sp.getBoolean("flag",false)){
            Intent intent =new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        sp.edit().putBoolean("flag",true).commit();
        fragments=new ArrayList<>();
        YdFragment ydFragment=new YdFragment();
        Yd2Fragment yd2Fragment =new Yd2Fragment();
        fragments.add(ydFragment); fragments.add(yd2Fragment);
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

//三秒跳转
//       new Thread(){
//           int num=3;
//            @Override
//            public void run() {
//                for (int i = num; i >0 ; i--){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Intent intent=new Intent(MainActivity.this,);
//                startActivity(intent);
//            }
//        }.start();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
