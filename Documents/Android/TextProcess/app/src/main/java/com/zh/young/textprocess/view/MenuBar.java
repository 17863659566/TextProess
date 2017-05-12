package com.zh.young.textprocess.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


/**
 * 用于显示菜单 工具栏，文件列表，用户信息 缩略图
 */
public class MenuBar extends LinearLayout {

    private MenuItem tools;
    private String TAG = "MenuBar";
    private MenuItem fileList;
    private MenuItem userInfo;

    public MenuBar(Context context) {
        super(context);

    }

    public MenuBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 将三个菜单项添加到菜单栏里面
     */
    private void addViews() {
        addView(tools);
        addView(fileList);
        addView(userInfo);
    }

    /**
     * 初始化三个Item
     */
    private void initViews() {
        tools = new MenuItem(getContext()) {
            @Override
            public void setPopupWindow(PopupWindow popupWindow) {
                setPopupWindowSize(popupWindow,500);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            }
        };

        fileList = new MenuItem(getContext()){

            @Override
            public void setPopupWindow(PopupWindow popupWindow) {
                setPopupWindowSize(popupWindow,500);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            }
        };

        userInfo = new MenuItem(getContext()) {
            @Override
            public void setPopupWindow(PopupWindow popupWindow) {
                setPopupWindowSize(popupWindow,500);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
            }
        };

        setItemInfo();
    }

    /**
     * 设置条目的信息
     */
    private void setItemInfo() {
        tools.setParams("工具栏");
        fileList.setParams("文件列表");
        userInfo.setParams("用户信息");
        Log.i(TAG,"信息设置完成");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG,"size = " + getMeasuredWidth());
        initViews();
        addViews();
    }

    /**
     * 设置popupwindow的高  宽默认为和手机等宽
     * @param popupWindow  需要设置的对象
     * @param height 需要设置的高
     */
    private void setPopupWindowSize(PopupWindow popupWindow, int height) {
        popupWindow.setWidth(getMeasuredWidth());
        popupWindow.setHeight(height);

    }
}
