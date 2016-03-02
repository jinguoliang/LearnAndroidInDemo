package com.example.jinux.mydemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jinux.mydemo.TestGoogleApi.TestGoogleApi;
import com.example.jinux.mydemo.animation.TestAnimation;
import com.example.jinux.mydemo.asynctask.SeeAsyncTask;
import com.example.jinux.mydemo.materail_animation.TouchFeedBack;
import com.example.jinux.mydemo.netstate.Netstate;
import com.example.jinux.mydemo.notification.ForegroundService;
import com.example.jinux.mydemo.reloadactivity.TestChangeConfig;
import com.example.jinux.mydemo.slidingmenu.SlidingMenuActivity;
import com.example.jinux.mydemo.actitionbar.ActitionBarDemo;
import com.example.jinux.mydemo.drawer.DrawerActivity;
import com.example.jinux.mydemo.storehouse.StoreHouseUsingStringArray;
import com.example.jinux.mydemo.tabhost.TestTabActivity;
import com.example.jinux.mydemo.task.GetActivityTask;
import com.example.jinux.mydemo.titanic.TitanicDemo;
import com.example.jinux.mydemo.xuanfukuang.XuanfukuangActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends ListActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_ACTIVITY = "key_activity";
    private static final String KEY_SERVICE = "key_service";


    private List<String> mTitleList;
    List<Map<String, Class<? extends Object>>> mMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        addActivity("悬浮框", XuanfukuangActivity.class);
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
        addService("通知", ForegroundService.class);
        addActivity("测试配置更改时是否加载 activity", TestChangeConfig.class);
        addActivity("AsycTask", SeeAsyncTask.class);
        addActivity("ActivityTask", GetActivityTask.class);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Class<?>> map = mMaps.get(position);
                if (map.get(KEY_ACTIVITY) != null) {
                    startActivity((Class<? extends Activity>) map.get(KEY_ACTIVITY));
                } else if(map.get(KEY_SERVICE) != null) {
                    startService((Class<? extends Service>) map.get(KEY_SERVICE));
                }

            }
        });
        Log.d("test", "main oncreate");
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
