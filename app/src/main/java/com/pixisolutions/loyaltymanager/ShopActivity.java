package com.pixisolutions.loyaltymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.pixisolutions.loyaltymanager.adapters.ShopAdapter;
import com.pixisolutions.loyaltymanager.models.Shop;
import com.pixisolutions.loyaltymanager.net.GsonRequest;
import com.pixisolutions.loyaltymanager.net.GsonRequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by sanjoy on 9/4/15.
 */
public class ShopActivity extends ActionBarActivity implements GsonRequestListener<Shop>{

    private static final String TAG = "ShopActivity";
    private List<Shop> shops = new ArrayList<Shop>();
    private ShopAdapter shopAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.listView);
        shopAdapter = new ShopAdapter(ShopActivity.this, R.layout.shop_layout, shops);
        listView.setAdapter(shopAdapter);
        Properties appProperties = new Properties();
        try{
            appProperties.load(getAssets().open("app.properties"));
        }catch (Exception exception){
            Log.e(TAG, exception.getMessage(), exception);
        }
        String url = appProperties.getProperty("server.url", "http://10.10.0.1:8000") + "/api/shop";
        GsonRequest request = new GsonRequest<Shop>(url, Shop.class, this);
        Volley.newRequestQueue(ShopActivity.this).add(request.perform());
    }


    @Override
    public void onErrorResponse(Exception error) {
        Log.e(TAG, error.getMessage(), error);
    }

    @Override
    public void onResponse(Shop shop) {
        shops.add(shop);
        shopAdapter.notifyDataSetChanged();
    }
}
