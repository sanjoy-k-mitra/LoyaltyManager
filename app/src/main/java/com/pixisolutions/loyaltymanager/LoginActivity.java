package com.pixisolutions.loyaltymanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by sanjoy on 8/26/15.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnNext;
    private EditText txtPhoneNumber;
    private Dialog dialog;
    private SharedPreferences settings;
    private SmsReceiver smsReceiver;
    private boolean isReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getString(R.string.login));

        btnNext = (Button)findViewById(R.id.btnNext);
        txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNumber);

        btnNext.setOnClickListener(this);
//        dialog = new Dialog(LoginActivity.this);
//        dialog.setContentView(R.layout.phone_verification_dialog);
//        dialog.findViewById(R.id.btnVerificationNext).setOnClickListener(this);

        settings = getPreferences(MODE_PRIVATE);
        if(settings.getBoolean(getString(R.string.number_verified), false) == true){
            continueToApp();
        }else {
            settings.edit()
                    .putString(getString(R.string.verification_code), String.valueOf(new Random().nextInt()))
                    .commit();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                smsReceiver = new SmsReceiver(txtPhoneNumber.getText().toString(), settings.getString(getString(R.string.verification_code), "123456"), this);
                registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
                isReceiverRegistered = true;
                PendingIntent pendingIntent = PendingIntent.getActivity(LoginActivity.this, 0, new Intent(this, LoginActivity.class), 0);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(txtPhoneNumber.getText().toString(), null, settings.getString(getString(R.string.verification_code), "12345"), pendingIntent, null);
//                dialog.show();
                break;

        }
    }

    public void continueToApp(){
        if(isReceiverRegistered){
            unregisterReceiver(smsReceiver);
        }
        settings.edit().putBoolean(getString(R.string.number_verified), true).commit();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
