package com.pixisolutions.loyaltymanager.net;

/**
 * Created by sanjoy on 8/31/15.
 */
public interface GsonRequestListener<T> {
    public void onResponse(T object);
    public void onErrorResponse(Exception error);
}
