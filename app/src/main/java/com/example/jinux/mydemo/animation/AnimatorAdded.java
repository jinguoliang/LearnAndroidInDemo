package com.example.jinux.mydemo.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuDialogHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jinux.mydemo.R;

/**
 * Created by baidu on 16/6/15.
 */
public class AnimatorAdded extends Activity {
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_added);
        mProgressbar = (ProgressBar) findViewById(R.id.progress);
        startAnimator();
        mProgressbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimator();
            }
        },3002);
    }

    private void startAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 1000);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgressbar.setProgress((Integer) animation.getAnimatedValue());
                Log.i("JIN", "value = " + animation.getAnimatedValue());
            }
        });
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }
}
