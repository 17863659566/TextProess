package com.zh.young.textpdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 来展示整体部分
 */
public class MainView extends EditText {

    private static final String TAG = "MainView";
    private static int linePosition = 0;
    private static int lineNumber = 1;
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
        Rect drawingRect = new Rect();
        getDrawingRect(drawingRect);
        int lineCount = getLineCount();
        //2. 获取行的边界
        Rect lineRect = new Rect();
        getLineBounds(lineCount-1,lineRect);
        Paint paint = new Paint();
        int lineHeight = getLineHeight();

        paint.setTextSize(18);
        paint.setColor(Color.WHITE);
        //绘制边界
        Paint paintRec = new Paint();
        paintRec.setColor(Color.BLACK);
        paintRec.setStyle(Paint.Style.STROKE);
        canvas.save();
        //绘制行号
        canvas.translate(0,50);
        int lineNumber = lineRect.bottom / lineHeight;
        for(int i = 0 ; i < lineNumber;i++){
            String text = "" + (i+1);

            canvas.drawText(text,0,text.length(),paint);


            canvas.translate(0,lineHeight);
        }

        canvas.restore();
        canvas.drawLine(50,drawingRect.top,50,drawingRect.bottom,paint);
        canvas.drawRect(new Rect(0,lineRect.top,lineRect.right,lineRect.bottom),paintRec);
        setPadding(100,50,0,0);
    }

}
