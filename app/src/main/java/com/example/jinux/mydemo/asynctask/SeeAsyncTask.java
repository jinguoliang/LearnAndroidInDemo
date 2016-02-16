package com.example.jinux.mydemo.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by baidu on 16/2/8.
 */
public class SeeAsyncTask extends Activity {
    private static final String TAG = "SeeAsyncTask";
    private static final String REQUEST_URL = "http://www.it-ebooks.info/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        loadData();
    }

    private void initView() {
        Button b = new Button(this);
        b.setText("goto");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SeeAsyncTask.this, SeeAsyncTask1.class));
            }
        });
        setContentView(b);
    }

    private void loadData() {
        AsyncTask task = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                StringBuilder builder = new StringBuilder();
                try {
                    URL url = new URL(params[0]);
                    URLConnection conn = url.openConnection();
                    conn.setDoInput(true);
                    conn.setConnectTimeout(5000);
                    InputStream in = conn.getInputStream();
                    BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                    String line = bin.readLine();
                    while (!TextUtils.isEmpty(line)) {
                        Log.d(TAG, "line: " + line);
                        builder.append(line);
                        line = bin.readLine();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return builder.toString();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d(TAG, "onPreExecute");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute");
            }
        }.execute(REQUEST_URL);
    }
}
