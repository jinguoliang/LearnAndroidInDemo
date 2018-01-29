package com.example.jinux.mydemo.remoteview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinux.mydemo.common.Utils;

/**
 * Created by Jinux on 17/2/9.
 */
public class TestRemoteView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("send remote");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.log("onClick");
                Intent intent = new Intent();
                intent.setAction("test_remote_view");
                sendBroadcast(intent);
            }
        });
        setContentView(button);
    }
}
