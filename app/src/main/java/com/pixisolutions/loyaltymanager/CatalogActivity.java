package com.pixisolutions.loyaltymanager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

/**
 * Created by sanjoy on 8/28/15.
 */
public class CatalogActivity extends ActionBarActivity implements GsonRequestListener<Offer> {
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
        GsonRequest request = new GsonRequest<Offer>("http://192.168.0.102:8000/api/offer/", Offer.class, this);
        Volley.newRequestQueue(CatalogActivity.this).add(request.perform());
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
}
