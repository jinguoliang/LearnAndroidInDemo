package com.example.jinux.mydemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jinux.mydemo.TestGoogleApi.TestGoogleApi;
import com.example.jinux.mydemo.actitionbar.ActitionBarDemo;
import com.example.jinux.mydemo.animation.AnimatorAdded;
import com.example.jinux.mydemo.animation.TestAnimation;
import com.example.jinux.mydemo.asynctask.SeeAsyncTask;
import com.example.jinux.mydemo.canvas.CanvasTest;
import com.example.jinux.mydemo.common.Utils;
import com.example.jinux.mydemo.design.ScrollingActivity;
import com.example.jinux.mydemo.design.TestCoordinatorLayout;
import com.example.jinux.mydemo.drawer.DrawerActivity;
import com.example.jinux.mydemo.event.TestEventConsume;
import com.example.jinux.mydemo.eventbus.TestEventBus;
import com.example.jinux.mydemo.experiment.CompileGolangActivity;
import com.example.jinux.mydemo.facedetact.FaceDetectDemo;
import com.example.jinux.mydemo.graphic.GifViewDemo;
import com.example.jinux.mydemo.inject.TestDagger;
import com.example.jinux.mydemo.materail_animation.TouchFeedBack;
import com.example.jinux.mydemo.netstate.Netstate;
import com.example.jinux.mydemo.notification.ForegroundService;
import com.example.jinux.mydemo.pendingintent.TestPendingIntent;
import com.example.jinux.mydemo.provider.document.DemoActivity;
import com.example.jinux.mydemo.recordscreen.RecordScreen;
import com.example.jinux.mydemo.reloadactivity.TestChangeConfig;
import com.example.jinux.mydemo.remoteview.RemoteActivity;
import com.example.jinux.mydemo.resouce.DrawableTest;
import com.example.jinux.mydemo.resouce.SpannableTest;
import com.example.jinux.mydemo.resouce.font.Fonts;
import com.example.jinux.mydemo.rxjava.TestRxJava;
import com.example.jinux.mydemo.s3.S3simple;
import com.example.jinux.mydemo.service.TestServiceDestroy;
import com.example.jinux.mydemo.slidingmenu.SlidingMenuActivity;
import com.example.jinux.mydemo.storehouse.StoreHouseUsingStringArray;
import com.example.jinux.mydemo.tabhost.TestTabActivity;
import com.example.jinux.mydemo.task.GetActivityTask;
import com.example.jinux.mydemo.testnotification.MNotification;
import com.example.jinux.mydemo.time.TestFunctionConsumeTime;
import com.example.jinux.mydemo.titanic.TitanicDemo;
import com.example.jinux.mydemo.tranact.TranslateActivity;
import com.example.jinux.mydemo.view.TestRotateTextView;
import com.example.jinux.mydemo.view.TestView;
import com.example.jinux.mydemo.view.TestViewPager;
import com.example.jinux.mydemo.view.VelocityTracker;
import com.example.jinux.mydemo.xuanfukuang.XuanfukuangActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_ACTIVITY = "key_activity";
    private static final String KEY_SERVICE = "key_service";
    private static final String KEY_RUNNABLE = "key_runnable";


    private List<String> mTitleList;
    List<Map<String, Object>> mMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Field sCurrentUser = null;
        try {
            sCurrentUser = Environment.class.getDeclaredField("sCurrentUser");
            sCurrentUser.setAccessible(true);
            Method[] methods = sCurrentUser.get(null).getClass().getDeclaredMethods();
            for (Method m : methods) {
                Utils.log("sCurrentUser = " +m.toGenericString());
            }
//            Method getExternalDirs = sCurrentUser.get(null).getClass().getDeclaredMethod("getExternalDirs");
//            Utils.log("" + getExternalDirs.toGenericString());
//            getExternalDirs.invoke(sCurrentUser.get(null));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        String a = null;
        if (a instanceof String) {
            Log.d(TAG, "null instanceof String");
        } else {
            Log.d(TAG, "null not instanceof String");
        }
        setContentView(R.layout.activity_main);
        mTitleList = new ArrayList<>();
        mMaps = new ArrayList<>();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTitleList));

        addActivity("Remote View", RemoteActivity.class);
        addActivity("check truanslate activity", TranslateActivity.class);
        addActivity("Share To YouTube", ShareToYoutube.class);
        addActivity("Canvas", CanvasTest.class);
        addActivity("Spannable", SpannableTest.class);
        addActivity("人脸识别",  FaceDetectDemo.class);
        addActivity("Drawable", DrawableTest.class);
        addActivity("Compile Golang", CompileGolangActivity.class);
        addActivity("Document Provider", DemoActivity.class);
        addActivity("PendingIntent", TestPendingIntent.class);
        addActivity("Gif",  GifViewDemo.class);
        addActivity("字体",  Fonts.class);
        addActivity("Drag", VelocityTracker.class);
        addActivity("动画叠加",  AnimatorAdded.class);
        addTask("测试通知",  new Runnable(){
            @Override
            public void run() {
                MNotification.showNotification(MainActivity.this);
            }
        });
        addActivity("时间检测",  TestFunctionConsumeTime.class);
        addActivity("调起 GooglePlay", GPInstanll.class);
        addActivity("录屏（首先 pc 执行adb tcpip 5555）", RecordScreen.class);
        addActivity("悬浮框", XuanfukuangActivity.class);
        addActivity("S3 simple", S3simple.class);
        addActivity("波浪效果", TitanicDemo.class);
        addActivity("抽屉", DrawerActivity.class);
        addActivity("Toolbar", ActitionBarDemo.class);
        addActivity("storehouse", StoreHouseUsingStringArray.class);
        addActivity("侧滑菜单", SlidingMenuActivity.class);
        addActivity("Material Animation", TouchFeedBack.class);
        addActivity("网络状态", Netstate.class);
        addActivity("TabHost", TestTabActivity.class);
        addActivity("TestGoogleApi", TestGoogleApi.class);
        addActivity("TestAnimation", TestAnimation.class);
        addService("通知<服务>", ForegroundService.class);
        addActivity("测试配置更改时是否加载 activity", TestChangeConfig.class);
        addActivity("AsycTask", SeeAsyncTask.class);
        addActivity("ActivityTask", GetActivityTask.class);
        addActivity("RxJava", TestRxJava.class);
        addActivity("EventBus", TestEventBus.class);
        addActivity("View", TestView.class);
        addActivity("CoordinatorLayout", TestCoordinatorLayout.class);
        addActivity("ViewPager", TestViewPager.class);
        addActivity("NestedScrolling", ScrollingActivity.class);
        addActivity("Dagger", TestDagger.class);
        addActivity("Simulate Service Crash", TestServiceDestroy.class);
        addActivity("RotateTextView", TestRotateTextView.class);
        addActivity("事件消费",  TestEventConsume.class);
        addTask("查看图片", new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File("/sdcard/B20161027102444749.jpg")), "image/jpeg");
                startActivity(intent);
            }
        });

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = mMaps.get(position);
                if (map.get(KEY_ACTIVITY) != null) {
                    startActivity((Class<? extends Activity>) map.get(KEY_ACTIVITY));
                } else if(map.get(KEY_SERVICE) != null) {
                    startService((Class<? extends Service>) map.get(KEY_SERVICE));
                } else if(map.get(KEY_RUNNABLE) != null) {
                    ((Runnable)map.get(KEY_RUNNABLE)).run();
                }

            }
        });
        Log.d("test", "main oncreate");

        String root = "/sdcard/";
        FileObserver observer = new FileObserver(root) {
            @Override
            public void onEvent(int event, String path) {
                Log.e("JIN", "onEvent");
                if (event == FileObserver.CREATE) {
                    Log.e("JIN", "create " + path);
                } else if (event == FileObserver.ACCESS) {
                    Log.e("JIN", "access " + path);
                } else if (event == FileObserver.MOVED_FROM) {
                    Log.e("JIN", "moved from " + path);
                }
            }
        };
        observer.startWatching();
    }

    private void addService(String title, Class<? extends Service> serviceClass) {
        mTitleList.add(title);
        HashMap map = new HashMap();
        map.put(KEY_SERVICE, serviceClass);
        mMaps.add(map);
    }

    private void addActivity(String title, Class<? extends Activity> activityClass) {
        mTitleList.add(title);
        HashMap map = new HashMap();
        map.put(KEY_ACTIVITY, activityClass);
        mMaps.add(map);
    }

    private void addTask(String title, Runnable task) {
        mTitleList.add(title);
        HashMap map = new HashMap();
        map.put(KEY_RUNNABLE, task);
        mMaps.add(map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startActivityTransition(Class<TouchFeedBack> touchFeedBackClass) {
        Intent i = new Intent(MainActivity.this, touchFeedBackClass);
        startActivity(i,
                ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
    }

    void startActivity(Class<? extends Activity> c) {
        Intent i = new Intent();
        i.setClass(MainActivity.this, c);
        startActivity(i);
    }

    void startService(Class<? extends Service> c) {
        Intent i = new Intent();
        i.setClass(MainActivity.this, c);
        startService(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
