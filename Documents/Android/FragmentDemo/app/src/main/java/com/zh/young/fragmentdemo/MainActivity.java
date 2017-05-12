package com.zh.young.fragmentdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 总结 ： 对于Fragment的使用分为两种方式
 *  方式一：静态使用,即在XML中直接引用   有点会用方便。缺点：不能够使用代码进行动态的调整
 *  方式二：动态使用，即在Java代码中直接使用  优点：能进行直接的操控，缺点：不够方便
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_switch2_1 = (Button) findViewById(R.id.btn_switch2_1);
        Button btn_switch1_2 = (Button) findViewById(R.id.btn_switch1_2);
        Button btn_delete1 = (Button) findViewById(R.id.btn_delete1);
        btn_delete1.setOnClickListener(this);
        btn_switch1_2.setOnClickListener(this);
        btn_switch2_1.setOnClickListener(this);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.frame1,new Fragment1Test(),"fragment1");
        transaction.commit();


    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.btn_switch2_1 :
                transaction.replace(R.id.frame1,new Fragment2Test());
                break;
            case R.id.btn_switch1_2 :
                break;
            case R.id.btn_delete1 :
                Fragment fragment1 = manager.findFragmentByTag("fragment1");
                transaction.remove(fragment1);
                transaction.addToBackStack(null);
                break;
        }
        transaction.commit();
    }
}
