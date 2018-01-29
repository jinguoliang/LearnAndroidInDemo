package com.example.jinux.mydemo.experiment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jinux.mydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jinux on 16/10/19.
 */

public class CompileGolangActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "CompileGolangActivity";
    private EditText mInput;
    private Button mRunBtn;
    private TextView mResult;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compile_golang);

        mInput = (EditText) findViewById(R.id.input);
        mResult = (TextView) findViewById(R.id.result);
        mRunBtn = (Button) findViewById(R.id.run);
        mRunBtn.setOnClickListener(this);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        String source = mInput.getText().toString();
        if (TextUtils.isEmpty(source)) {
            Toast.makeText(this, "empty source", Toast.LENGTH_LONG).show();
            return;
        }

        runGolang(source, new OnResulter() {
            @Override
            public void onResult(String s) {
                mResult.setText(s);
            }
        });
    }

    private void runGolang(final String source, final OnResulter onResulter) {
        String compileUrl = "http://golang.org/compile";
        Log.d(TAG, "runGolang: source = " + source);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, compileUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "response -> " + response);
                        String result = getContent(response);
                        onResulter.onResult(result);
                        mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                            @Override
                            public boolean apply(Request<?> request) {
                                return true;
                            }
                        });
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("version", "2");
                Log.d(TAG, "getParams: source = " + source);
                map.put("body", source);
                return map;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Log.d(TAG, "getBody: ");
                return super.getBody();
            }

            @Override
            public String getBodyContentType() {
                Log.d(TAG, "getBodyContentType: ");
                return super.getBodyContentType();
            }


        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
    }

    private String getContent(String response) {
        try {
            JSONObject object = new JSONObject(response);

            // 检查错误
            String err = object.optString("Errors");
            if (!TextUtils.isEmpty(err)) {
                return err;
            }

            // 返回结果
            String result = object.getJSONArray("Events").getJSONObject(0).getString("Message");
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface OnResulter {
        void onResult(String s);
    }

}
