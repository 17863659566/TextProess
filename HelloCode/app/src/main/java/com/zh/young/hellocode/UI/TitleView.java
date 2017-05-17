package com.zh.young.hellocode.UI;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zh.young.hellocode.R;


/**
 * 这个view用来显示在文件管理器的头部
 */
public class TitleView extends RelativeLayout {

    private static ImageButton backArrow;
    private String mTitle;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getDefStyle(attrs);
        addViews();
    }

    /**
     * 获取自定义属性
     * @param attrs
     */
    private void getDefStyle(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleView);
        mTitle = typedArray.getText(R.styleable.TitleView_title).toString();
    }

    private void addViews() {
        TextView title = new TextView(getContext());   //用来显示选择文件
        //用来显示返回箭头
        backArrow = new ImageButton(getContext());
        backArrow.setBackgroundResource(R.drawable.ic_arrow_back_black_24dp);
        title.setText(mTitle);
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
