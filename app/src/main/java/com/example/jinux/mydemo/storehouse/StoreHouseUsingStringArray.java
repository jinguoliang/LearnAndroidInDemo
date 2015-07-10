package com.example.jinux.mydemo.storehouse;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jinux.mydemo.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class StoreHouseUsingStringArray extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.fragment_storehouse_header);

        ImageView imageView = (ImageView) findViewById(R.id.store_house_ptr_image);
        imageView.setImageResource(R.drawable.hellojava);

        final PtrFrameLayout frame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        final StoreHouseHeader header = new StoreHouseHeader(this);

        // using string array from resource xml file
        header.initWithStringArray(R.array.storehouse);

        frame.setDurationToCloseHeader(3000);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.autoRefresh(false);
            }
        }, 100);

        // change header after loaded
        frame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                if (mLoadTime % 2 == 0) {
                    header.setScale(1);
                    header.initWithStringArray(R.array.storehouse);
                } else {
                    header.setScale(0.5f);
                    header.initWithStringArray(R.array.akta);
                }
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, int oldPosition, int currentPosition, float oldPercent, float currentPercent) {

            }
        });

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 20);
            }
        });
    }
}
