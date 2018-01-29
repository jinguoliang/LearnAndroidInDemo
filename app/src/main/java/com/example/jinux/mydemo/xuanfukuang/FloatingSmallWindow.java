package com.example.jinux.mydemo.xuanfukuang;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinux.mydemo.common.Utils;

/**
 * Created by jinux on 6/1/15.
 */
public class FloatingSmallWindow extends LinearLayout {
    private final WindowManager windowManager;
    private float xInView;
    private float yInView;
    private float xInWindow;
    private float yInWindow;
    private WindowManager.LayoutParams mParams;

    public FloatingSmallWindow(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        TextView tv = new TextView(getContext());
        tv.setText("hello");
        tv.setBackgroundColor(Color.CYAN);
        this.addView(tv);
        setBackgroundColor(Color.BLUE);
        TextView tv1 = new TextView(getContext());
        tv1.setText("hello" +
                "hdhdhdf");
        tv1.setBackgroundColor(Color.YELLOW);
        tv1.setVisibility(View.INVISIBLE);
        this.addView(tv1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
               setVisibility(View.INVISIBLE);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       setVisibility(View.VISIBLE);
                    }
                }, 3000);
            }
        }, 5000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xInWindow = event.getRawX();
                yInWindow = event.getRawY() - Utils.getStatusBarHeight(getContext());
                break;
            case MotionEvent.ACTION_MOVE:
                xInWindow = event.getRawX();
                yInWindow = event.getRawY() - Utils.getStatusBarHeight(getContext());
                updateViewPosition();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setmParams(WindowManager.LayoutParams mParams) {
        this.mParams = mParams;
    }

    private void updateViewPosition() {
        mParams.x = (int) (xInWindow - xInView);
        mParams.y = (int) (yInWindow - yInView);
        windowManager.updateViewLayout(this, mParams);
    }
}
