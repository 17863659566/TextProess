package com.zh.young.textpdemo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 意图：在编辑文件的过程中，随时的检查文件的内容中是否有关键字存在，如果存在那么将其颜色变为深色
 * 1. 如果文件的内容有变化，则启动线程进行文件内容检测
 * 2. 如果内容中含有关键字，那么将其变为深色
 *
 *  问题一： 如果去检测文本是否发生变化
 *  问题二： 在文本发生变化的时候如何去匹配这些变化的文本
 *  问题三： 在检测出具有关键字的文本如何去染色
 */
public class MainActivity extends AppCompatActivity {
    private Editor mEditor;
    private String mContent;
    private int test;
    private Pattern pattern = Pattern.compile("\\W");
    //问题二： 在文本发生变化的时候如何去匹配这些变化的文本
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            match();
            //mEditor.setSelection(mContent.length());

        }
    };
    private int start;
    private int end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //问题一： 如果去检测文本是否发生变化
        mEditor = (Editor) findViewById(R.id.editor);
        new Thread() {
            @Override
            public void run() {
                mEditor.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //从这个方法的测试结果看，这个在文本的输入过程中，其实是一个替换的过程，将前面输入的字符串用后面的字符串加上前面的字符串显示出来
                        //before 这个应该是向前编辑为0，向后编辑为1  代表一种行为
                        mContent = s.toString();
                        if(pattern.matcher(mContent.substring(start,start+count)).matches()){
                            mHandler.sendEmptyMessage(0);

                        }
//                        Log.i("msg","文本发生变化");

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }.start();


    }

    /**
     * 在这个match问题上
     */
    public void match() {

        //这个可以用关键词来代替
        Pattern HTML_TAGS = Pattern.compile(
                "\\Winclude|\\w+\\W");
        Matcher matcher = HTML_TAGS.matcher(mContent);

        SpannableString allText = new SpannableString(mContent);
        String s = allText.toString();
        Pattern pattern = Pattern.compile("\\W");
            while (matcher.find()) {
                //Log.i("text", s + matcher.start() + matcher.end());
                if(pattern.matcher(mContent.substring(matcher.start(),matcher.start()+1)).matches()){
                    start = matcher.start() + 1;
                }else{
                    start =matcher.start();
                }
                if (pattern.matcher(mContent.substring(matcher.end()-1,matcher.end())).matches()){
                    end = matcher.end() - 1;
                }else{
                    end = matcher.end();
                }
                allText.setSpan(
                        new ForegroundColorSpan(Color.BLUE),
                        start,
                        end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.i("text",(test++) + "");
            }
            mEditor.setText(allText);
            mEditor.setSelection(mContent.length());
        }
    }

