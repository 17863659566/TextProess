package com.zh.young.textpdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

public class Editor extends EditText {
    public Editor(Context context) {
        super(context);
        setGravity(Gravity.TOP);
        setBackground(null);
    }

    public Editor(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackground(null);
        setGravity(Gravity.TOP);
    }

    public Editor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.TOP);
        setBackground(null);
    }


    /*@Override
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
    }*/

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }
}
