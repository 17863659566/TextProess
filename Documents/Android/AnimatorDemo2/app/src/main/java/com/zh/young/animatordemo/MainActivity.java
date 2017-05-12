package com.zh.young.animatordemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 这个project的作用是用于验证Fragment的宽和高的获取方式，以及验证Fragment的动画
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        addFragments();

    }

    private void findViews() {
        Button anim01 = (Button) findViewById(R.id.anim01);
        anim01.setOnClickListener(this);
    }


    private void addFragments() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.frame01,new AnimatorFragment1(),"frame01");
        transaction.add(R.id.frame02,new AnimatorFragment2(),"frame02");
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.anim01 :
                Fragment frame01 = getFragmentManager().findFragmentByTag("frame01");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.animator.animator_enter,R.animator.animator_exit);
                transaction.hide(frame01);
                transaction.commit();

                int height = frame01.getView().getMeasuredHeight();
                Toast.makeText(this, "高度为" + height, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
