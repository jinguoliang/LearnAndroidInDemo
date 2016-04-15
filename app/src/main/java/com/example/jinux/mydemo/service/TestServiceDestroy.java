package com.example.jinux.mydemo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.example.jinux.mydemo.common.Utils;

/**
 * Created by baidu on 16/4/15.
 */
public class TestServiceDestroy extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Utils.log("uncaughtException");
            }
        });

        bindService(new Intent(this, MService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Utils.log("onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Utils.log("onServiceDisconnected");

            }
        }, BIND_AUTO_CREATE);
    }
}
