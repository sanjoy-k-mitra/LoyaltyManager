package com.pixisolutions.loyaltymanager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixisolutions.loyaltymanager.models.Item;
import com.pixisolutions.loyaltymanager.net.GsonRequest;
import com.pixisolutions.loyaltymanager.net.GsonRequestListener;

import java.io.InvalidObjectException;
import java.util.Properties;
import java.util.logging.Logger;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, GsonRequestListener<Item>{

    private static final String TAG = "HomeActivity";
    protected Button btnScan;
    protected Button btnHistory;
    protected Button btnShop;
    protected GsonRequest<Item> itemGsonRequest;
    protected Properties appProperties;
    protected Dialog dialog;

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

        appProperties = new Properties();
        try{
            appProperties.load(getAssets().open("app.properties"));
        }catch (Exception exception){
            Log.e(TAG, exception.getMessage(), exception);
        }

        dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.reward_dialog);
        dialog.findViewById(R.id.btnOk).setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                String contents = result.getContents();
                if (contents != null) {
                    String url = appProperties.getProperty("server.url", "http://10.10.0.1:8000") + "/api/item/searchCode?code=" + contents;
                    itemGsonRequest = new GsonRequest<Item>(url, Item.class, HomeActivity.this);
                    Volley.newRequestQueue(HomeActivity.this).add(itemGsonRequest.perform());
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
                integrator.setCaptureActivity(CaptureActivity.class);
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
            case R.id.btnOk:
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onResponse(Item item) {
        ((TextView)dialog.findViewById(R.id.dspPointReceived)).setText(item.getPoint().toString());
        dialog.show();
    }

    @Override
    public void onErrorResponse(Exception error) {
        Log.e(TAG, error.getMessage(), error);
    }
}
