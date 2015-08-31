package com.pixisolutions.loyaltymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pixisolutions.loyaltymanager.R;

import com.pixisolutions.loyaltymanager.models.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanjoy on 8/31/15.
 */
public class OfferAdapter extends ArrayAdapter<Offer> {

    List<Offer> offers;

    public OfferAdapter(Context context, int resource, List<Offer> offers) {
        super(context, resource, offers);
        this.offers = offers;
    }

    public OfferAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.offer_layout, parent, false);
        TextView dspDesc = (TextView) rowView.findViewById(R.id.dspDescription);
        TextView dspPoint = (TextView) rowView.findViewById(R.id.dspPoint);
        dspDesc.setText(offers.get(position).getDescription());
        dspPoint.setText(String.valueOf(offers.get(position).getPoint()));
        return rowView;
    }
}
