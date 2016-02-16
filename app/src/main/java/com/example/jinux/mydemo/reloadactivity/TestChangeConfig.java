package com.example.jinux.mydemo.reloadactivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by baidu on 16/1/28.
 */
public class TestChangeConfig extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "onCreate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("test", "onConfigurationChanged");
    }
}
