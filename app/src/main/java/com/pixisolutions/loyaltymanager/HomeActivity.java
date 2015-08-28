package com.pixisolutions.loyaltymanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.InvalidObjectException;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnScan;
    Button btnHistory;
    Button btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnScan = (Button)findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);

        btnHistory = (Button)findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(this);

        btnShop = (Button)findViewById(R.id.btnShop);
        btnShop.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                String contents = result.getContents();
                if (contents != null) {
                    Toast.makeText(HomeActivity.this, contents, Toast.LENGTH_LONG).show();
                } else {
                    throw new NullPointerException("No Content Found");
                }
            } else {
                throw new InvalidObjectException("Could not detect object");
            }
        }catch (Exception e){
            Toast.makeText(HomeActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnScan:
                IntentIntegrator integrator = new IntentIntegrator(HomeActivity.this);
                integrator.initiateScan();
                break;
            case R.id.btnHistory:
                Intent historyIntent = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(historyIntent);
                break;
            case R.id.btnShop:
                Intent shopIntent = new Intent(HomeActivity.this, CatalogActivity.class);
                startActivity(shopIntent);
                break;
        }
    }
}
