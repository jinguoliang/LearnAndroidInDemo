package com.example.jinux.mydemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.jinux.mydemo.slidingmenu.SlidingMenuActivity;
import com.example.jinux.mydemo.xuanfukuang.XuanfukuangActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list = new ArrayList<>();
        list.add("悬浮框");
        list.add("侧滑菜单");
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();

                switch (position){
                    case 0:
                        i.setClass(MainActivity.this,XuanfukuangActivity.class);
                        break;
                    case 1:
                        i.setClass(MainActivity.this, SlidingMenuActivity.class);
                        break;
                }

                startActivity(i);
            }
        });
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
