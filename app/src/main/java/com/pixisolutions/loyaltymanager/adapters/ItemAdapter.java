package com.pixisolutions.loyaltymanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pixisolutions.loyaltymanager.R;
import com.pixisolutions.loyaltymanager.models.Item;

import java.util.List;

/**
 * Created by sanjoy on 8/31/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    List<Item> items;

    public ItemAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_layout, parent, false);
        TextView dspName = (TextView) rowView.findViewById(R.id.dspName);
        TextView dspPoint = (TextView) rowView.findViewById(R.id.dspPoint);
        dspName.setText(items.get(position).getName());
        dspPoint.setText(String.valueOf(items.get(position).getPoint()));
        return rowView;
    }

}
