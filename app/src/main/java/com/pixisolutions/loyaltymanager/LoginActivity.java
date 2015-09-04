package com.pixisolutions.loyaltymanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sanjoy on 8/26/15.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnNext;
    private EditText txtPhoneNumber;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnNext = (Button)findViewById(R.id.btnNext);
        txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNumber);

        btnNext.setOnClickListener(this);
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.phone_verification_dialog);
        dialog.findViewById(R.id.btnVerificationNext).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVerificationNext:
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnNext:
                dialog.show();
                break;

        }
    }
}
