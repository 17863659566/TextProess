package com.zh.young.textprocess.view;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zh.young.textprocess.entity.ConstantValue;

/**
 * 由这个类来提供每个menu的item的显示
 */
public abstract class MenuItem extends android.support.v7.widget.AppCompatTextView{


    private static final String TAG = "MenuItem";
    /**
     * 用来显示弹窗
     */
    private PopupWindow mPopupWindow;

    private int popupwindow_status = ConstantValue.MENU_POPUPWINDOW_HIDE;

    public MenuItem(Context context) {
        super(context);
        initViews();
    }

    public MenuItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MenuItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    /**
     * 初始化TextView和Popupwindow
     */
    protected void initViews(){

        mPopupWindow = new PopupWindow();

        setPopupWindow(mPopupWindow);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果处于隐藏状态，那么弹出popupwindow，并且设置状态为显示状态
                if (popupwindow_status == ConstantValue.MENU_POPUPWINDOW_HIDE) {
                    mPopupWindow.showAsDropDown(MenuItem.this, 100, 0);
                    popupwindow_status = ConstantValue.MENU_POPUPWINDOW_DISPLAY;
                } else {
                    //如果popupwindow为显示状态，那么更改状态为隐藏状态，并且对其进行隐藏
                    mPopupWindow.dismiss();
                    popupwindow_status = ConstantValue.MENU_POPUPWINDOW_HIDE;
                }
                Log.i(TAG, "tools已被点击"+mPopupWindow.getWidth()+"---"+mPopupWindow.getHeight());
            }
        });
    }



    /**
     * 抽象出popupwindow的属性设置
     * @param popupWindow  窗体对象
     */
    public abstract void setPopupWindow(PopupWindow popupWindow);

    /**
     * 设置布局参数
     * @param ItemName  设置的名字
     */
    public void setParams(String ItemName) {
        setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0f));
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        setText(ItemName);
        setTextColor(Color.WHITE);
    }

}
