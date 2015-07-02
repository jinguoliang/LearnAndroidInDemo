package com.example.jinux.mydemo.drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

public class DrawerActivity extends ActionBarActivity {

    private DrawerLayout dl_navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        dl_navigator = (DrawerLayout)findViewById(R.id.main_dl);
        dl_navigator.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                Utils.showToast(DrawerActivity.this,"onDrawerStateChanged = " + newState);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Utils.showToast(DrawerActivity.this, "onDrawerClosed " );

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Utils.showToast(DrawerActivity.this, "onDrawerOpened " );

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Utils.log("onDrawerSlide = " + slideOffset);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
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

    public void toggleDrawer(View view) {
        if (dl_navigator.isDrawerOpen(Gravity.START)){
            dl_navigator.closeDrawer(Gravity.START);
        }else{
            dl_navigator.openDrawer(Gravity.START);
        }
    }
}
