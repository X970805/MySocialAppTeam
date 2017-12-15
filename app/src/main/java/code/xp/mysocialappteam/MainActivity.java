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
import code.xp.mysocialappteam.activity.ThridActivity;
import code.xp.mysocialappteam.fragment.Yd2Fragment;
import code.xp.mysocialappteam.fragment.YdFragment;

public class MainActivity extends AppCompatActivity {


    /**
     * 加状态 ，第二次登录直接到数据界面
     */
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        if (sp.getBoolean("flag", false)) {
            new Thread() {
                int num = 3;
                @Override
                public void run() {
                    for (int i = num; i > 0; i--) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent = new Intent(MainActivity.this, ThridActivity.class);
                    startActivity(intent);
                }
            }.start();
        }
        new Thread() {
            int num = 3;
            @Override
            public void run() {
                for (int i = num; i > 0; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}
