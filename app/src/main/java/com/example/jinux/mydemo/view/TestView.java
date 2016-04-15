package com.example.jinux.mydemo.view;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.EditText;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

/**
 * Created by baidu on 16/3/28.
 * function:
 */
public class TestView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.test_view);

        View v = new View(this){
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                Utils.log("view onTouchEvent");
                return super.onTouchEvent(event);
            }
        };
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utils.log("listener onTouchEvent");
                return false;
            }
        });
        setContentView(v);
    }

    public void onText2Click(View view) {
        Log.e("JIN", "text2");
    }

    public void onText1Click(View view) {
        Log.e("JIN", "text1");
    }

    public void onBtnClick(View view) {
        Log.e("JIN", "btn");
    }
}
