package com.example.jinux.mydemo.actitionbar;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

public class ActitionBarDemo extends AppCompatActivity {

    private Toolbar mToolbar;
    private int mCurrentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actition_bar_demo);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setBackgroundColor(Color.BLUE);
        mToolbar.setTitle("hello");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast(ActitionBarDemo.this, "Navigation click");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actition_bar_demo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        onPrepareMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mCurrentGroup = R.id.setting;
        }else{
            mCurrentGroup = 0;
        }
        invalidateOptionsMenu();
        return true;
    }

    public void onPrepareMenu() {
        Menu menu = mToolbar.getMenu();
        for (int i = 0, count = menu.size(); i < count; i++) {
            MenuItem item = menu.getItem(i);
            item.setVisible(item.getGroupId() == mCurrentGroup);
        }
    }
}
