package com.example.jinux.mydemo.materail_animation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jinux.mydemo.R;

/**
 * Created by jinux on 7/13/15.
 */
public class TestMaterialTransition extends Activity {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tranisition);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onImageClick(View view) {
        ImageView iv = (ImageView) view;

        AnimatedVectorDrawable d = (AnimatedVectorDrawable) getDrawable(R.drawable.animvectordrawable);
        iv.setImageDrawable(d);
        d.start();



    }
}
