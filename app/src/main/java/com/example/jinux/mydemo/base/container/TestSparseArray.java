package com.example.jinux.mydemo.base.container;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;

/**
 * Created by Jinux on 16/9/22.
 */

public class TestSparseArray extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(8);
    }
}
