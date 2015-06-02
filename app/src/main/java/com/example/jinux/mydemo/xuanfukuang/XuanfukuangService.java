package com.example.jinux.mydemo.xuanfukuang;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class XuanfukuangService extends Service {
    private Timer timer;
    private FloatingSmallWindow smallWindow;
    private WindowManager.LayoutParams smallWindowParams;

    public XuanfukuangService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new RefreshTask(), 0, 500);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        timer.cancel();
        timer = null;
    }

    android.os.Handler handler = new android.os.Handler();

    private class RefreshTask extends TimerTask {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                    int screenWidth = windowManager.getDefaultDisplay().getWidth();
                    int screenHeight = windowManager.getDefaultDisplay().getHeight();
                    if (smallWindow == null) {
                        smallWindow = new FloatingSmallWindow(getApplicationContext());
                        if (smallWindowParams == null) {
                            smallWindowParams = new WindowManager.LayoutParams();
                            smallWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                            smallWindowParams.format = PixelFormat.RGBA_8888;
                            smallWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                            smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                            smallWindowParams.width = 200;
                            smallWindowParams.height = 200;
                            smallWindowParams.x = screenWidth;
                            smallWindowParams.y = screenHeight / 2;
                        }
                        smallWindow.setmParams(smallWindowParams);
                        windowManager.addView(smallWindow, smallWindowParams);
                    }

                }
            });
        }
    }


}
