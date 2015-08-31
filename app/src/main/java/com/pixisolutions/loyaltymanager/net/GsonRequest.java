package com.pixisolutions.loyaltymanager.net;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

/**
 * Created by sanjoy on 8/31/15.
 */

public class GsonRequest<T> implements Response.Listener<JSONArray>, Response.ErrorListener{
    private static final String TAG = "GsonRequest";
    private String url;
    private GsonRequestListener gsonRequestListener;
    private Class<T> clazz;

    public GsonRequest( String url, Class<T> clazz,  GsonRequestListener<T> gsonListener) {
        this.url = url;
        this.clazz = clazz;
        this.gsonRequestListener = gsonListener;
    }

    public JsonArrayRequest perform(){
        JsonArrayRequest request = new JsonArrayRequest(url, this, this);
        return request;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage(), error);
        gsonRequestListener.onErrorResponse(error);
    }

    @Override
    public void onResponse(JSONArray response) {
        JsonParser parser = new JsonParser();
        Gson gson =  new Gson();
        for(int i = 0; i < response.length(); i++){
            try{
                JsonElement object = parser.parse(response.getJSONObject(i).toString());
                T o = gson.fromJson(object, clazz);
                gsonRequestListener.onResponse(o);
            }catch (Exception error){
                Log.e(TAG, error.getMessage(), error);
            }
        }
    }
}
