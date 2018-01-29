package com.example.jinux.mydemo.time;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import com.example.jinux.mydemo.common.Utils;

import java.io.File;

/**
 * Created by Jinux on 17/1/20.
 */
public class TestFunctionConsumeTime extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 遍历文件
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testtime";
        File dirFile = new File(path);

        File files[] = null;
        String fileNames[] = null;
        if (dirFile.isDirectory()) {
            Utils.tick();
            files = dirFile.listFiles();
            Utils.tock("listFiles");
        }

        Utils.tick();
        if (files != null) {
            for (File f : files) {
                f.getName();
            }
        }
        Utils.tock("foreach name");

    }

}
