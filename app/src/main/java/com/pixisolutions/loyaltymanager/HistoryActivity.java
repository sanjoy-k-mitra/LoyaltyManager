package com.pixisolutions.loyaltymanager;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;
import com.pixisolutions.loyaltymanager.adapters.ItemAdapter;
import com.pixisolutions.loyaltymanager.models.Item;
import com.pixisolutions.loyaltymanager.models.Offer;
import com.pixisolutions.loyaltymanager.net.GsonRequest;
import com.pixisolutions.loyaltymanager.net.GsonRequestListener;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by sanjoy on 8/28/15.
 */
public class HistoryActivity extends ActionBarActivity implements GsonRequestListener<Item> {

    private static final String TAG = "HistoryActivity";
    private ListView listView;
    private ItemAdapter itemAdapter;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        items = new ArrayList<Item>();
        listView = (ListView)findViewById(R.id.listView);
        itemAdapter = new ItemAdapter(HistoryActivity.this, R.layout.item_layout, items);
        listView.setAdapter(itemAdapter);
        Properties appProperties = new Properties();
        try{
            appProperties.load(getAssets().open("app.properties"));
        }catch (Exception exception){
            Log.d(TAG, exception.getMessage(), exception);
        }
        String url = appProperties.getProperty("server.url", "http://10.10.0.1:8000") + "/api/item";
        GsonRequest request = new GsonRequest<Item>(url, Item.class, this);
        Volley.newRequestQueue(HistoryActivity.this).add(request.perform());
    }

    @Override
    public void onErrorResponse(Exception error) {
        Log.e(TAG, error.getMessage(), error);
    }

    @Override
    public void onResponse(Item item) {
        items.add(item);
        itemAdapter.notifyDataSetChanged();
    }
}
