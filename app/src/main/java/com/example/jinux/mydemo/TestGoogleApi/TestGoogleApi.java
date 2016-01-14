package com.example.jinux.mydemo.TestGoogleApi;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuDialogHelper;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinux.mydemo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.ExponentialBackOff;

import java.util.Arrays;


/**
 * Created by baidu on 15/12/10.
 */
public class TestGoogleApi extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTv;
    private GoogleAccountCredential credential;
    private static final int REQUEST_ACCOUNT_PICKER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_google_api);

        credential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(Auth.SCOPES));
        credential.setBackOff(new ExponentialBackOff());
        mStatusTv = (TextView) findViewById(R.id.conStatus);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
//        startActivityForResult(credential.newChooseAccountIntent(),
//                REQUEST_ACCOUNT_PICKER);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mStatusTv.setText("onConnected");
        if (!mGoogleApiClient.isConnected()) {
            mStatusTv.setText("re failed");
            return;
        }

        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) == null) {
            mStatusTv.setText("current person null");
            return ;
        }

        String name = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient).getDisplayName();
        ((TextView) findViewById(R.id.googleName)).setText("the google name = " + name);

    }

    @Override
    public void onConnectionSuspended(int i) {
        mStatusTv.setText("onConnectionSuspended");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        mStatusTv.setText("onConnectionFailed" + connectionResult.getErrorCode());
        try {
            connectionResult.startResolutionForResult(this, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == Activity.RESULT_OK && data != null
                        && data.getExtras() != null) {
                    String accountName = data.getExtras().getString(
                            AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        Toast.makeText(this, "the account name = " + accountName, Toast.LENGTH_LONG).show();
                        credential.setSelectedAccountName(accountName);
                    }
                }
                break;
        }
    }
}
