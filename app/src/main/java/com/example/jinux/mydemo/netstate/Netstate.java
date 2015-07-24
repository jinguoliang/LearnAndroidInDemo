package com.example.jinux.mydemo.netstate;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DebugUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jinux.mydemo.R;
import com.example.jinux.mydemo.common.Utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class Netstate extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netstate);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            if (info.isAvailable()) {
                Utils.showToast(this, "网络连接");
                Utils.log(info.getExtraInfo());
                Utils.log(info.getTypeName() + "--" + info.getSubtypeName());
//                Network[] netwoks = manager.getAllNetworks();
//                for (Network n : netwoks) {
////                    manager.setProcessDefaultNetwork(n);
//                    NetworkInfo i = manager.getNetworkInfo(n);
//                    Utils.log(i.toString());
//                    try {
//                        for(InetAddress addr : n.getAllByName(null)){
//
//                        }
//                    } catch (UnknownHostException e) {
//                        e.printStackTrace();
//                    }
//                    if (i.isAvailable()) {
//                        LinkProperties prop = manager.getLinkProperties(n);
//                        Utils.log(prop.toString());
//                        NetworkCapabilities cap = manager.getNetworkCapabilities(n);
//                        Utils.log("load " + cap.getLinkDownstreamBandwidthKbps() + " upStream " + cap.getLinkUpstreamBandwidthKbps());
//                    }
//                }
            } else {
                Utils.showToast(this, "网络未连接");
            }
        } else {
            Utils.showToast(this, "网络未连接");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_netstate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
