package com.zh.young.hellocode.UI;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;

public class Editor extends EditText {
    public Editor(Context context) {
        this(context,null);

    }

    public Editor(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Editor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.TOP);
        setBackground(null);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
        setTextColor(Color.WHITE);
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }
}
