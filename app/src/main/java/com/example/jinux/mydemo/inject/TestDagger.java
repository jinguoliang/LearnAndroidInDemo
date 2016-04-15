package com.example.jinux.mydemo.inject;

import android.app.Activity;
import android.os.Bundle;

import com.example.jinux.mydemo.R;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by baidu on 16/4/14.
 */
public class TestDagger extends Activity {

    @Inject
    Coder coder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dagger);
        ObjectGraph.create(AppModule.class).inject(this);
    }
}
