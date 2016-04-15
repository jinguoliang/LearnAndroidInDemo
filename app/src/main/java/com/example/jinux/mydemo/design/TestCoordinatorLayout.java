package com.example.jinux.mydemo.design;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jinux.mydemo.R;

import java.util.ArrayList;

/**
 * Created by baidu on 16/3/30.
 * function:
 */
public class TestCoordinatorLayout extends Activity {
    private static final String TAG = "TestCoordinatorLayout";
    private ViewPager mViewPager;
    private ArrayList<View> viewContainer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_coordinator);
        bindFABListener();
        initRecycleView();
        initAppBar();
        initTab();
        prepareData();
        initViewPager();
    }

    private void prepareData() {
        for (int i = 0; i < 4; i++) {
            TextView v = new TextView(this);
            int color = Color.BLUE;
            switch (i) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.GRAY;
                    break;
                case 2:
                    color = Color.LTGRAY;

                    break;
                case 3:
                    color = Color.YELLOW;

                    break;
                default:
            }
            v.setMinHeight(3000);
            v.setBackgroundColor(color);
            viewContainer.add(v);
        }
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewContainer.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainer.get(position));
                return viewContainer.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView(viewContainer.get(position));
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }
        });
    }

    private void initTab() {
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("hello").setIcon(R.drawable.ic_launcher).setContentDescription("hello hello wow"));
        tabs.addTab(tabs.newTab().setText("world").setContentDescription("world wow"));
        tabs.addTab(tabs.newTab().setText("wow").setContentDescription("wowowowowowo"));
    }

    private void initAppBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
    }

    private void initRecycleView() {
    }

    private void bindFABListener() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                                Log.i(TAG, "cancel click");
                            }
                        })
                        .show();
            }
        });
    }
}
