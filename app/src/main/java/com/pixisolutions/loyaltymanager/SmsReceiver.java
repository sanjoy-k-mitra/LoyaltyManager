package com.pixisolutions.loyaltymanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by sanjoy on 9/11/15.
 */
public class SmsReceiver extends BroadcastReceiver {

    String incomingNumber;
    String expectedMessage;
    LoginActivity loginActivity;

    public SmsReceiver(String incomingNumber, String expectedMessage, LoginActivity loginActivity){
        this.incomingNumber = incomingNumber;
        this.expectedMessage = expectedMessage;
        this.loginActivity = loginActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if(bundle != null){
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for(int i = 0; i < pdus.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                if(msgs[i].getOriginatingAddress().compareTo(incomingNumber) == 0){
                    if(msgs[i].getMessageBody().compareTo(expectedMessage) == 0){
                        abortBroadcast();
                        loginActivity.continueToApp();
                    }
                }
            }
        }
    }
}
