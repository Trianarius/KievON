package com.kievon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Places;

/**
 * Created by Bogdan on 20.12.2015.
 */

public class Authorization extends FragmentActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

private GoogleApiClient mGoogleApiClient;
private TextView mStatusTextView;

@Override
protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);



//        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

//        BuildConfig.VERSION_NAME;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
        .build();

        }


@Override
public void onConnected(Bundle bundle) {

        }

@Override
public void onConnectionSuspended(int i) {

        }

@Override
public void onConnectionFailed(ConnectionResult connectionResult) {

        }
public  void signIn(){
        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signinIntent, 1);
        }
private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
        new ResultCallback<Status>() {
@Override
public void onResult(Status status) {
        // [START_EXCLUDE]
        updateUI(false);
        // [END_EXCLUDE]
        }
        });
        }
private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
        new ResultCallback<Status>() {
@Override
public void onResult(Status status) {
        // [START_EXCLUDE]
        status.getStatus();
        System.out.println(status);
        updateUI(false);
        // [END_EXCLUDE]
        }
        });
        }
@Override
public void onClick(View v) {
//        Log.d("123", "Text");
//        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signinIntent, 1);
        switch (v.getId()) {
                case R.id.sign_in_button:
                signIn();

                        break;
                case R.id.back:
                        Intent intent = new Intent(Authorization.this, MainActivity.class);
                        startActivity(intent);

                        break;


                case R.id.disconnect_button:

                        revokeAccess();

                        break;
        }
        }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleSignInResult(result);
        }
        }
private void handleSignInResult(GoogleSignInResult result){
        Log.d("Tag", "handlesignInResult" + result.isSuccess());
        //show acc if Success sigIn
        GoogleSignInAccount acct = result.getSignInAccount();
        // mStatusTextView.setText(getString(R.id.signed_in_fmt, acct.getDisplayName()));

        }
private void updateUI(boolean signedIn) {
        if (signedIn) {
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);

        } else {
        // mStatusTextView.setText(R.string.sign_out);

        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);

        }
        }


}
