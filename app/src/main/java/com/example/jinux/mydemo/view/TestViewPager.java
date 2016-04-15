package com.example.jinux.mydemo.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jinux.mydemo.R;

import java.util.ArrayList;

/**
 * Created by baidu on 16/3/31.
 * function:
 */
public class TestViewPager extends Activity {
    private ViewPager mViewPager;
    private ArrayList<View> viewContainer = new ArrayList<>();
    private ArrayList<String> titleContainer = new ArrayList<>();
    private PagerTabStrip tabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpager);

        prepareData();
        tabStrip = (PagerTabStrip) findViewById(R.id.tabstrip);
        tabStrip.setDrawFullUnderline(true);
        tabStrip.setBackgroundColor(Color.BLUE);
        tabStrip.setTabIndicatorColor(Color.RED);
        tabStrip.setTextSpacing(200);
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

            @Override
            public CharSequence getPageTitle(int position) {
                return titleContainer.get(position);
            }
        });
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                Log.d("JIN", ((TextView)page).getText().toString() + " pos = " + position);
                page.setPivotX(position < 0 ? 0 : page.getWidth());
                page.setRotationY(position * -40);
                page.setTranslationX(Math.abs(position)*page.getWidth());
            }
        });
    }

    private void prepareData() {
        titleContainer.add("hello");
        titleContainer.add("world");
        titleContainer.add("你好");
        titleContainer.add("世界");

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
            v.setBackgroundColor(color);
            v.setText(titleContainer.get(i));
            viewContainer.add(v);
        }
    }
}
