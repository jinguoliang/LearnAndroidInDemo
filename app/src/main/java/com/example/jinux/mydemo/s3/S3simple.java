/***
 * Copyright (c) 2012 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.example.jinux.mydemo.s3;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

public class S3simple extends Activity {

	private static final int FILE_SELECT_CODE = 0;
	
	Button select;
	Button interrupt;
	ProgressBar progress;
	TextView status;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.s3simple);
		
		select = (Button) findViewById(R.id.btn_select);
		interrupt = (Button) findViewById(R.id.btn_interrupt);
		progress = (ProgressBar) findViewById(R.id.progress);
		status = (TextView) findViewById(R.id.status);
		
		select.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(S3simple.this, UploadService.class);
				intent.putExtra(UploadService.ARG_FILE_PATH, "/sdcard/gamerecorder/Game Show_20160609_12.mp4");
				startService(intent);
			}
		});
		
		interrupt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// interrupt any active upload
				Intent intent = new Intent(UploadService.UPLOAD_CANCELLED_ACTION);
				sendBroadcast(intent);
			}
		});
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		IntentFilter f = new IntentFilter();
		f.addAction(UploadService.UPLOAD_STATE_CHANGED_ACTION);
		registerReceiver(uploadStateReceiver, f);
	}

	@Override
	protected void onStop() {
		unregisterReceiver(uploadStateReceiver);
		super.onStop();
	}
	
	private String getPathFromContentUri(Uri uri) {
		String path = uri.getPath();
		if (uri.toString().startsWith("content://")) {
			String[] projection = { MediaStore.MediaColumns.DATA,MediaStore.MediaColumns.HEIGHT };
			ContentResolver cr = getApplicationContext().getContentResolver();
			Cursor cursor = cr.query(uri, null, null, null, null);
			if (cursor != null) {
				try {
					if (cursor.moveToFirst()) {
						path = cursor.getString(0);
					}
				} finally {
					cursor.close();
				}
			}

		}
		return path;
	}
	 
	private BroadcastReceiver uploadStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	Bundle b = intent.getExtras();
        	status.setText(b.getString(UploadService.MSG_EXTRA));
        	int percent = b.getInt(UploadService.PERCENT_EXTRA);
        	progress.setIndeterminate(percent < 0);
        	progress.setProgress(percent);
        }
    };

}
