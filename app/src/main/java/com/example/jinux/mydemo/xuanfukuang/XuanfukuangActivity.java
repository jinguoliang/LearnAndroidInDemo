package com.example.jinux.mydemo.xuanfukuang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jinux.mydemo.R;

/**
 * Created by jinux on 6/1/15.
 */
public class XuanfukuangActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanfukuang);
    }

    public void onButtonClick(View view) {
        Intent i = new Intent(this,XuanfukuangService.class);
        startService(i);
    }


}
