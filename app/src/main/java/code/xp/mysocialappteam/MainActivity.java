package code.xp.mysocialappteam;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.present.MyPresent;
import code.xp.mysocialappteam.utils.MyApp;
import code.xp.mysocialappteam.view.activity.SecondActivity;
import code.xp.mysocialappteam.view.activity.ThridActivity;

public class MainActivity extends AppCompatActivity implements MyControl {


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
                    finish();
                }
            }.start();
        } else {
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
                    finish();
                }
            }.start();
        }



    }

    @Override
    public void equipment(String s) {
        System.out.println("/////////////////////////////");
        String uuid = MyApp.getUuid(getBaseContext(), getContentResolver());
        System.out.println("_______________" + uuid);
        MyPresent myPresent = new MyPresent(this);
        myPresent.setequipment(uuid);
    }

}
