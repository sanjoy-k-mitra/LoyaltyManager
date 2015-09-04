package com.pixisolutions.loyaltymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;
import com.pixisolutions.loyaltymanager.adapters.OfferAdapter;
import com.pixisolutions.loyaltymanager.models.Offer;
import com.pixisolutions.loyaltymanager.net.GsonRequest;
import com.pixisolutions.loyaltymanager.net.GsonRequestListener;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by sanjoy on 8/28/15.
 */
public class CatalogActivity extends ActionBarActivity implements GsonRequestListener<Offer>, AdapterView.OnItemClickListener {
    private static final String TAG = "CatalogActivity";
    private List<Offer> offers = new ArrayList<Offer>();
    private OfferAdapter offerAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.listView);
	    offerAdapter = new OfferAdapter(CatalogActivity.this, R.layout.offer_layout, offers);
        listView.setAdapter(offerAdapter);
        Properties appProperties = new Properties();
        try{
            appProperties.load(getAssets().open("app.properties"));
        }catch (Exception exception){
            Log.d(TAG, exception.getMessage(), exception);
        }
        String url = appProperties.getProperty("server.url", "http://10.10.0.1:8000") + "/api/offer";
        GsonRequest request = new GsonRequest<Offer>(url, Offer.class, this);
        Volley.newRequestQueue(CatalogActivity.this).add(request.perform());
        listView.setOnItemClickListener(CatalogActivity.this);
    }


    @Override
    public void onErrorResponse(Exception error) {
        Log.e(TAG, error.getMessage(), error);
    }

    @Override
    public void onResponse(Offer offer) {
        offers.add(offer);
        offerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(CatalogActivity.this, ShopActivity.class);
        startActivity(intent);
    }
}
