package com.zh.young.textprocess.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zh.young.textprocess.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //开启一个线程，睡眠2s后，转向main页面，并且销毁Splash界面
        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    //关闭当前activity
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
