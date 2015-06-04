package com.example.jinux.mydemo.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jinux on 6/3/15.
 */
public class SlidingMenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SlidingMenuView view = new SlidingMenuView(this);
        setContentView(view);

        TextView tv = new TextView(this);
        tv.setText("hello worlddd");
        tv.setEnabled(false);
        view.setContent(tv);

        Button b = new Button(this) ;
        b.setEnabled(false);
        b.setClickable(false);
        b.setText("hello kdkdkd");
        view.setMenu(b);
    }
}
