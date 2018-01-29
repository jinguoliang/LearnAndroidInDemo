package com.example.jinux.mydemo.testnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuDialogHelper;

import com.example.jinux.mydemo.MainActivity;
import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

/**
 * Created by baidu on 16/6/29.
 */
public class MNotification {
    public static void showNotification(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder mBuilder = new Notification.Builder(context);
        mBuilder.setContentIntent(pendingIntent)
                .setTicker("ha")
                .setContentTitle("Hello")
                .setContentText("hello world")
                .setAutoCancel(false);
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1123, mBuilder.getNotification());
        Utils.log("notify");
    }
}
