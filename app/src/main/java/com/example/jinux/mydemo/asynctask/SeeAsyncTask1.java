package com.example.jinux.mydemo.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by baidu on 16/2/8.
 */
public class SeeAsyncTask1 extends Activity{
    private static final String TAG = "SeeAsyncTask1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
}
