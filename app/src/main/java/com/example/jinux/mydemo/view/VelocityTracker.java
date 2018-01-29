package com.example.jinux.mydemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by Jinux on 16/10/9.
 */

public class VelocityTracker extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MoveToAnotherLayoutDemo(this));
    }

    public static class MoveToAnotherLayoutDemo extends LinearLayout {

        private ViewGroup vg;
        private FrameLayout frameLayout;

        public MoveToAnotherLayoutDemo(Context context) {
            super(context);

            addFrameLayout(context);
            addViewGroup(context);

            vg.post(new Runnable() {
                @Override
                public void run() {
                    View v = frameLayout.getChildAt(0);
                    frameLayout.removeAllViews();
                    vg.addView(v);
                }
            });

        }

        private void addViewGroup(Context context) {
            vg = new ViewGroup(context) {
                @Override
                protected void onLayout(boolean changed, int l, int t, int r, int b) {

                }
            };
            vg.setBackgroundColor(Color.BLUE);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params1.weight = 1;

            addView(vg, params1);

        }

        private void addFrameLayout(Context context) {
            TextView view = new TextView(context);
            view.setText("hahaha");
            view.setBackgroundColor(Color.CYAN);
            view.offsetLeftAndRight(100);

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, 200);
            params.topMargin = 300;
            params.leftMargin = 300;

            frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(Color.WHITE);
            frameLayout.addView(view, params);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params1.weight = 1;

            addView(frameLayout, params1);
        }
    }

    public static class Drag extends RelativeLayout {

        private ViewDragHelper mViewDragHelper;
        private boolean mIsRelease;

        private void animatedDelete(final Button button) {
            final ViewGroupOverlay viewGroupOverlay = this.getOverlay();
            viewGroupOverlay.add(button);
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(button, "scaleX", 1, 3f),
                    ObjectAnimator.ofFloat(button, "scaleY", 1, 3f),
                    ObjectAnimator.ofFloat(button, "alpha", 1, 0.0f)
            );
            set.start();
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    viewGroupOverlay.remove(button);
                }
            });
        }

        public Drag(Context context) {
            super(context);
            setBackgroundColor(Color.CYAN);

            final Button button = new Button(context);
            button.setText("hello world");
            button.setTextSize(50);
            button.setBackgroundColor(Color.RED);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    animatedDelete(button);
                }
            });
            RelativeLayout.LayoutParams params = new LayoutParams(300, 300);
            addView(button, params);

            mViewDragHelper = ViewDragHelper.create(this, 0.5f, new ViewDragHelper.Callback() {
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    return true;
                }

                @Override
                public void onViewCaptured(View capturedChild, int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                    mIsRelease = false;
                }

                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    return left;
                }

                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return top;
                }

                @Override
                public void onViewReleased(View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    mIsRelease = true;
                    mViewDragHelper.flingCapturedView(0, 0, 780, 1620);
                    postInvalidate();
                }

                @Override
                public int getViewHorizontalDragRange(View child) {
                    return getMeasuredWidth() - child.getMeasuredWidth();
                }

                @Override
                public int getViewVerticalDragRange(View child) {
                    return getMeasuredHeight() - child.getMeasuredHeight();
                }
            });
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.i(TAG, "onDraw: ");
        }

        @Override
        public void computeScroll() {
            super.computeScroll();
            Log.i(TAG, "computeScroll: ");
            if (mViewDragHelper.continueSettling(true)) {
                Log.i(TAG, "computeScroll: continueSettling");
                postInvalidate();
            }
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return mViewDragHelper.shouldInterceptTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mViewDragHelper.processTouchEvent(event);
            return true;
        }

    }

}
