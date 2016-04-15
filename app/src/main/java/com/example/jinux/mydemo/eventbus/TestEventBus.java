package com.example.jinux.mydemo.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;


/**
 * Created by baidu on 16/4/1.
 * function:
 */
public class TestEventBus extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new MessageEvent("hello world"));
    }

    public class MessageEvent {
        public final String msg;

        public MessageEvent(String msg) {
            this.msg = msg;
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(MessageEvent event) {
        Toast.makeText(this, "receive event:" + event.msg, Toast.LENGTH_LONG).show();
    }
}
