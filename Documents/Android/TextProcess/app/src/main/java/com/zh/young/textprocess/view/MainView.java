package com.zh.young.textprocess.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 来展示整体部分
 */
public class MainView extends EditText {

    /**
     * 测试Log的时候使用
     */
    private static final String TAG = "MainView";
    /**
     * 获取整个View的框架信息
     */
    private Rect mDrawingRect;
    /**
     * 获取行的统计信息
     */
    private int mLineCount;
    /**
     * 获取行的框架信息
     */
    private Rect mLineRect;
    /**
     * 绘制数字的时候使用
     */
    private Paint mPaint;
    /**
     * 获取行高的时候使用
     */
    private int mLineHeight;
    /**
     * 绘制背景图的时候使用
     */
    private Paint mPaintRec;
    /**
     * 为了判断以上所有参数的判断
     */
    private boolean isInitParams = false;

    public MainView(Context context) {
        super(context);

    }


    public MainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 获取状态栏的高度
     */
    private int getStatusHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        Log.i(TAG, "高度为" + result);
        return result;
    }



    //获取parent以及获取布局中的相关组件，要在onSizeChanged中调用
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        getStatusHeight();

    }


    /**
     * 为用户打开一个文本设置
     * @param onContentListener  内容的设置接口
     */
    public void setOnContentListener(OnContentListener onContentListener){
        String content = onContentListener.setContent();
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
        setText(content);
        setTextColor(Color.WHITE);

    }
    public interface OnContentListener{
        String setContent();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. 获取整个view的边界信息
        if(!isInitParams){
            initParams();
            isInitParams = true;
        }

        canvas.save();
        //绘制行号
        canvas.translate(0,50);
        int lineNumber = mLineRect.bottom / mLineHeight;
        for(int i = 0 ; i < lineNumber;i++){
            String text = "" + (i+1);

            canvas.drawText(text,0,text.length(), mPaint);


            canvas.translate(0, mLineHeight);
        }

        canvas.restore();
        canvas.drawLine(50, mDrawingRect.top,50, mDrawingRect.bottom, mPaint);
        //canvas.drawRect(new Rect(0,lineRect.top,lineRect.right,lineRect.bottom),paintRec);
        setPadding(100,0,0,0);
    }

    /**
     * 初始化绘制参数的方法
     */
    private void initParams() {
        mDrawingRect = new Rect();
        getDrawingRect(mDrawingRect);
        mLineCount = getLineCount();
        //2. 获取行的边界
        mLineRect = new Rect();
        getLineBounds(mLineCount -1, mLineRect);
        mPaint = new Paint();
        mLineHeight = getLineHeight();

        mPaint.setTextSize(18);
        mPaint.setColor(Color.WHITE);
        //绘制边界
        mPaintRec = new Paint();
        mPaintRec.setColor(Color.BLACK);
        mPaintRec.setStyle(Paint.Style.STROKE);
    }

}
