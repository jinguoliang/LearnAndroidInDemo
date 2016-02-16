package com.example.jinux.mydemo.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.jinux.mydemo.R;

/**
 * Created by baidu on 16/1/14.
 */
public class ForegroundService extends Service {

    private static final int ONGOING_NOTIFICATION_ID = 2;
    private final Handler mHandler;

    int index = 0;

    public ForegroundService() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("test", "index = " + index++);
                if (index == 20) {
                    stopSelf();
                    return;
                }
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        };
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Context context = this;
        Intent notificationIntent = new Intent(context, Test.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        String title = "test";
        String ticker = "test";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setTicker(ticker)
                .setContentTitle(title)
                .setContentText(title)
                .setPriority(1000)
                .addAction(R.drawable.ic_launcher, "test", pendingIntent)
                .addAction(R.drawable.ic_launcher, "test", pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher);
        startForeground(ONGOING_NOTIFICATION_ID, mBuilder.build());
        mHandler.sendEmptyMessageDelayed(1, 1000);
        return super.onStartCommand(intent, flags, startId);
    }
}
