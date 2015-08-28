package com.pixisolutions.loyaltymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sanjoy on 8/26/15.
 */
public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btnNext;
    private EditText txtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnNext = (Button)findViewById(R.id.btnNext);
        txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNumber);

        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
