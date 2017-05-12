package com.zh.young.textprocess.activity;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zh.young.textprocess.R;
import com.zh.young.textprocess.entity.ConstantValue;
import com.zh.young.textprocess.entity.GlobalVariable;
import com.zh.young.textprocess.fragment.MainFragment;
import com.zh.young.textprocess.fragment.MenuFragment;
import com.zh.young.textprocess.fragment.OpenFileFragment;
import com.zh.young.textprocess.view.MainView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;


/**
 * 主面板页面，负责大部分的展示内容
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private Fragment mainFragment;
    private Fragment menuFragment;
    private MainView main_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();

    }

    private void initFragments() {
        mainFragment = new MainFragment();
        menuFragment = new MenuFragment();

        addFragment(menuFragment, R.id.frame_menu, "menuFragment");
        addFragment(mainFragment, R.id.frame_main, "mainFragment");


    }

    private void addFragment(Fragment fragment, int container, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.add(container, fragment, tag);
        transaction.commit();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (GlobalVariable.has_open_file == ConstantValue.NOT_HAVE_FILE_OPEN) {
            main_view = (MainView) mainFragment.getView().findViewById(R.id.main_view);

            setMainViewProperty();
        }


    }

    /**
     * 修改光标颜色   使用了反射  在TextView里面拿到了这个属性值
     */
    private void modifyMainViewCursorColor() {


        try {
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            field.set(main_view, R.drawable.cursor_color);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //如果用户点击的位置属于中间的位置
                //1.获取中间位置坐标，并且左右各减100px
                //计算获得

                if (GlobalVariable.has_open_file == ConstantValue.NOT_HAVE_FILE_OPEN) {
                    openFileFragment();


                }

                break;
        }
        return true;
    }

    private void performDisplayAnimation() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.show(menuFragment);
        Toast.makeText(this, "即将显示", Toast.LENGTH_SHORT).show();
        transaction.commit();
    }

    /**
     * 打开文件
     */
    private void openFileFragment() {
        startActivityForResult(new Intent(this, FileManagerActivity.class), 1);

    }

    /**
     * 为MenuFragment执行过渡动画
     */
    private void performHideAnimation() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.hide(menuFragment);
        transaction.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        String filePath = data.getStringExtra("filePath");
//        Toast.makeText(this, "filePath = " + filePath, Toast.LENGTH_SHORT).show();

        //下面进行文件处理，并将文件读入TextView
        readFile(filePath);
    }

    /**
     * 从文件中读取数据到TextView中
     *
     * @param filePath
     */
    private void readFile(String filePath) {

        try {
            //1. 读取文件
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            final String text = buffer.toString();
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();


            main_view.setOnContentListener(new MainView.OnContentListener() {
                @Override
                public String setContent() {
                    return text;
                }

            });

            main_view.setFocusable(true);
            main_view.setGravity(Gravity.LEFT);
            main_view.setFocusableInTouchMode(true);  //默认的情况下显示光标
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "文件读取错误，请稍后再试", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "文件读取异常，请稍后再试", Toast.LENGTH_SHORT).show();
        }

        GlobalVariable.has_open_file = ConstantValue.HAVE_FILE_OPEN;
    }

    private void setMainViewProperty() {
        main_view.setFocusable(false);
        main_view.setText("打开文件");
        main_view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        main_view.setGravity(Gravity.CENTER);
        main_view.setTextColor(Color.WHITE);
        main_view.setBackground(null);
        modifyMainViewCursorColor();

        //光标颜色不能设置  通过线索可知，可以通过源码来获取
        //通过实际使用EditText可以发现，这个控件会自动的进行拼写检查，那么可不可以通过这个方法来进行字段的匹配呢？
    }


}
