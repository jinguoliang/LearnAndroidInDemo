package com.example.jinux.mydemo.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jinux.mydemo.R;

/**
 * Created by baidu on 15/12/15.
 */
public class TestAnimation extends Activity {
    private View mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_animation);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAnimation.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                testViewAnimate();
            }
        }, 1000);
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                testValueAnimator();
            }
        }, 3000);
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                testObjectAnimator();
            }
        }, 5000);
        mButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                testObjectAnimator_PropertyValuesHolder();
            }
        }, 7000);
    }

    private void testObjectAnimator_PropertyValuesHolder() {
        // 一个值时代表目标状态
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 200);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(mButton, propertyValuesHolder);
        oa.setDuration(1000);
        oa.start();
    }

    private void testObjectAnimator() {
        mButton.setTranslationX(500);
        // 一个值时代表目的状态
        ObjectAnimator oa = ObjectAnimator.ofFloat(mButton, View.TRANSLATION_X, 200);
        oa.setDuration(1000);
        oa.start();
    }

    private void testValueAnimator() {
        findViewById(R.id.rb).setVisibility(View.GONE);
        ValueAnimator va = ValueAnimator.ofInt(200);
        va.setDuration(1000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mButton.getLayoutParams();
                params.width = v;
                params.height = v;
                mButton.setLayoutParams(params);
            }
        });
        va.start();
    }

    private void testViewAnimate() {
        mButton.animate().translationYBy(200).start();
    }
}
