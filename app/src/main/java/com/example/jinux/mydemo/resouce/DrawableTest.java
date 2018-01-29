package com.example.jinux.mydemo.resouce;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

/**
 * Created by Jinux on 16/11/7.
 */

public class DrawableTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout layout = new FrameLayout(this);
        setContentView(layout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        final View v = new View(this);
        v.setBackground(getDrawable(R.drawable.shadow));
        layout.addView(v, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        v.post(new Runnable() {
            @Override
            public void run() {
                Utils.log("the width = " + v.getWidth());
                Utils.log("the height = " + v.getHeight());
            }
        });
    }
}
