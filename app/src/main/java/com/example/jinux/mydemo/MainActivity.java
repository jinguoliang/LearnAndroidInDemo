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
import java.util.List;


public class MainActivity extends ListActivity {

    private static final String TAG = "MainActivity";

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
        List<String> list = new ArrayList<>();
        list.add("悬浮框");
        list.add("波浪效果");
        list.add("抽屉");
        list.add("Toolbar");
        list.add("storehouse");
        list.add("侧滑菜单");
        list.add("Material Animation");
        list.add("网络状态");
        list.add("TabHost");
        list.add("TestGoogleApi");
        list.add("TestAnimation");
        list.add("通知");
        list.add("测试配置更改时是否加载 activity");
        list.add("AsycTask");
        list.add("ActivityTask");
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(XuanfukuangActivity.class);

                        break;
                    case 1:
                        startActivity(TitanicDemo.class);
                        break;
                    case 2:
                        startActivity(DrawerActivity.class);
                        break;
                    case 3:
                        startActivity(ActitionBarDemo.class);
                        break;
                    case 4:
                        startActivity(StoreHouseUsingStringArray.class);
						break;
					case 5:
                        startActivity(SlidingMenuActivity.class);
                        break;
                    case 6:
                        startActivityTransition(TouchFeedBack.class);
                        break;
                    case 7:
                        startActivity(Netstate.class);
                        break;
                    case 8:
                        startActivity(TestTabActivity.class);
                        break;
                    case 9:
                        startActivity(TestGoogleApi.class);
                        break;
                    case 10:
                        startActivity(TestAnimation.class);
                        break;
                    case 11:
                        startService(ForegroundService.class);
                        break;
                    case 12:
                        startActivity(TestChangeConfig.class);
                        break;
                    case 13:
                        startActivity(SeeAsyncTask.class);
                        break;
                    case 14:
                        startActivity(GetActivityTask.class);
                        break;
                }

            }
        });
        Log.d("test", "main oncreate");
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
