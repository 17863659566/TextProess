package com.zh.young.setcolordemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        TextView textView = (TextView) findViewById(R.id.text_view);

        Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");

        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 40, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(wordtoSpan);
    }
}
