package com.zh.young.textprocess.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zh.young.textprocess.R;


/**
 * 这个view用来显示在文件管理器的头部
 */
public class TitleView extends RelativeLayout {

    private static ImageButton backArrow;

    public TitleView(Context context) {
        super(context);
        addViews();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addViews();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addViews();
    }

    private void addViews() {
        TextView title = new TextView(getContext());   //用来显示选择文件
        //用来显示返回箭头
        backArrow = new ImageButton(getContext());
        backArrow.setBackgroundResource(R.drawable.ic_arrow_back_black_24dp);
        title.setText("选择文件");
        title.setTextColor(Color.WHITE);


        //添加箭头
        LayoutParams imageLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_LEFT);
        addView(backArrow, imageLayoutParams);
            //将title显示在中间位置
        LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(title, titleLayoutParams);



    }

    /**
     * 为用户开启一个实现接口
     * @param onArrowClickListener
     */
    public void setOnArrowClickListener(final OnArrowClickListener onArrowClickListener){
            backArrow.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArrowClickListener.onclick();
                }
            });
    }

    public interface OnArrowClickListener{
        void onclick();
    }


}
