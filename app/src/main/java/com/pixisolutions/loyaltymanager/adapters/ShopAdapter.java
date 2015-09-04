package com.pixisolutions.loyaltymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pixisolutions.loyaltymanager.R;
import com.pixisolutions.loyaltymanager.models.Shop;

import java.util.List;

/**
 * Created by sanjoy on 9/4/15.
 */
public class ShopAdapter extends ArrayAdapter<Shop> {

    protected List<Shop> shops;

    public ShopAdapter(Context context, int resource, List<Shop> objects) {
        super(context, resource, objects);
        this.shops = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shop_layout, parent, false);
        TextView dspName = (TextView) rowView.findViewById(R.id.dspShopName);
        TextView dspAddress = (TextView) rowView.findViewById(R.id.dspAddress);
        dspName.setText(shops.get(position).getName());
        dspAddress.setText(String.valueOf(shops.get(position).getAddress()));
        return rowView;
    }
}
