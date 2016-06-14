package com.example.jinux.mydemo.recordscreen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.jinux.mydemo.common.Utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by baidu on 16/6/6.
 */
public class RecordScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button v = new Button(this);
        setContentView(v);
        v.setText("Record");
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
//                        Looper.prepare();
                        try {
                            Process p = Runtime.getRuntime().exec("adb kill-server && adb start-server");
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            Utils.log("haha:" + bufferedReader.readLine());
                            p = Runtime.getRuntime().exec("adb discoonnect 127.0.0.1:5555 &&" +
                                            " adb connect 127.0.0.1");
                            bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            Utils.log("haha:" + bufferedReader.readLine());
                            Process process = Runtime.getRuntime().exec("adb devices");
                            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            final BufferedReader finalBufferedReader = bufferedReader;
                            v.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Utils.showToast(RecordScreen.this, "haha:" + finalBufferedReader.readLine() + "\n" +
                                                finalBufferedReader
                                                        .readLine()
                                                + "\n" + finalBufferedReader.readLine()
                                                + "\n" + finalBufferedReader.readLine());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

//                    OutputStream out = process.getOutputStream();
//                    BufferedOutputStream bo = new BufferedOutputStream(out);
//                    PrintStream printStream = new PrintStream(bo);
//                    printStream.print("screenrecord /sdcard/test.mp4 --time-limit 10;");
//                    Utils.log("haha:" + bufferedReader.readLine() + "\n" + bufferedReader.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }
}
