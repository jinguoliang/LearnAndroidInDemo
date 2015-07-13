package com.example.jinux.mydemo.materail_animation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

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
}
