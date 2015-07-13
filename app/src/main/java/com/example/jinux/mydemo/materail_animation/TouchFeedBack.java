package com.example.jinux.mydemo.materail_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.ImageView;

import com.example.jinux.mydemo.R;

/**
 * Created by jinux on 7/13/15.
 */
public class TouchFeedBack extends Activity {
    private ImageView hello;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_feed_back);
        hello = (ImageView) findViewById(R.id.hello);

        getWindow().setEnterTransition(new Explode());
//        getWindow().setExitTransition(new Explode());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onShowClick(View view) {
        if (hello.getVisibility() != View.VISIBLE) {
            int cx = (hello.getLeft() + hello.getRight()) / 2;
            int cy = (hello.getTop() + hello.getBottom()) / 2;
            int finalRadius = Math.max(hello.getHeight(), hello.getWidth());
            Animator anim = ViewAnimationUtils.createCircularReveal(hello, cx, cy, 0, finalRadius);
            hello.setVisibility(View.VISIBLE);
            anim.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onHideClick(View view) {
        if (hello.getVisibility() == View.VISIBLE) {
            int cx = (hello.getLeft() + hello.getRight()) / 2;
            int cy = (hello.getTop() + hello.getBottom()) / 2;
            int initRadius = hello.getWidth();
            Animator anim = ViewAnimationUtils.createCircularReveal(hello, cx, cy, initRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    hello.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onTransitionClick(View view) {
        Intent i = new Intent(this, TestMaterialTransition.class);
        startActivity(i,
                ActivityOptions.makeSceneTransitionAnimation(this,hello,"hello").toBundle());
    }
}
