package com.zh.young.animatordemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.imageview);

    }

    /**
     * 使用ObjectAnimator来直接操作
     * @param view
     */
    public void ObjectAnimatorTest(View view){
        ObjectAnimator
                .ofFloat(
                view,
                "rotationX",
                0.0f,
                360f
        )
                .setDuration(1000)
                .start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        propertyValuesHolder(mView);
        return true;
    }

    /**
     * 组合动画，使用ObjectAnimator来实现
     */
    public void rotateyAnimRun(final View view){
        final ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "zl", 1.0f, 0.0f)
                .setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                view.setAlpha(value);
                view.setScaleX(value);
                view.setScaleY(value);

            }
        });
    }

    /**
     * 实现抛物线的效果,没做出来
     */
    public void parabolaAnimator(final View view){
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "zl", 200)
                .setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                view.setTranslationX(value);
                view.setTranslationY(value);
            }


        });

    }
    /**
     * 使用简单的方式实现组合效果
     */
    public void propertyValuesHolder(View view)
    {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
    }
}
