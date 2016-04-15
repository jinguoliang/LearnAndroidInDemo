package com.example.jinux.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by baidu on 16/4/15.
 */
public class MService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new RuntimeException("crash");
    }
}
