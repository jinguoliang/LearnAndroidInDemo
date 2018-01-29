package com.example.jinux.mydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by baidu on 16/4/8.
 */
public class GPInstanll extends Activity {

    static final String PLAY_PKG_NAME = "com.android.vending";

    @Bind(R.id.gpPkg)
    EditText mPkgEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gp_install);
        ButterKnife.bind(this);
    }

    public void onGoGPClick(View view) {
        String pkg = mPkgEt.getText().toString();
        if (TextUtils.isEmpty(pkg)) {
            showToast(R.string.pls_input_pkg);
            return;
        }
//        gotoGP(pkg);
    }

    private void gotoGP(String pkg) {
        String url = "https://play.google.com/store/apps/details?id=" + pkg;
        Intent playIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        playIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        playIntent.setPackage(PLAY_PKG_NAME);
        try {
            startActivity(playIntent);
        } catch (Exception e) {
            // no play crash
            startBrowser(this, url);
        }
    }

    public void startBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PackageManager pManager = context.getPackageManager();
        ResolveInfo defaultInfo = pManager.resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (defaultInfo == null) {
            showToast(R.string.no_browser);
        } else {
            ActivityInfo actInfo = defaultInfo.activityInfo;
            if ("android" .equals(actInfo.packageName)) {
                // No default set, it's resolver
                // Use the first item as preffered candidate
                List<ResolveInfo> infos = pManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                actInfo = infos.get(0).activityInfo;
                intent.setPackage(actInfo.packageName);
            }
            context.startActivity(intent);
        }
    }

    public void showToast(int res) {
        Toast.makeText(this, res, Toast.LENGTH_LONG).show();
    }
}
