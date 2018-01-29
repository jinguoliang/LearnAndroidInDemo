package com.example.jinux.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.jinux.mydemo.common.Utils;

import java.io.File;

/**
 * Created by Jinux on 17/1/22.
 */
public class ShareToYoutube extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("video/mp4");
        intent.putExtra(Intent.EXTRA_TITLE, "hello");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/sdcard/recordmaster/20161124_172326.mp4")));
        intent.putExtra(Intent.EXTRA_SUBJECT, "hello world");
        intent.putExtra(Intent.EXTRA_TEXT, "tag1, tag2");
        try {
            startActivityForResult(Intent.createChooser(intent,"Upload video via:"), 2);
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utils.log("requestCode = " + requestCode);
        Utils.log("resultCode = " + resultCode);
        Utils.log("data = " + data);
    }
}
