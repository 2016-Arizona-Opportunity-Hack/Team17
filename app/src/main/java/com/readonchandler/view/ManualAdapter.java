package com.readonchandler.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.readonchandler.R;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Manual;

/**
 * Created by Lakshmisagar on 10/2/2016.
 */

public class ManualAdapter extends BaseAdapter {
    private static final String TAG = ManualAdapter.class.getName();
    Context mContext;
    LayoutInflater inflater;
    private List<Manual> manual_events_list = new ArrayList<Manual>();

    public ManualAdapter(Context context, List<Manual> manualevents) {
        mContext = context;
        manual_events_list = manualevents;
    }

    @Override
    public int getCount() {
        return manual_events_list.size();
    }

    @Override
    public Object getItem(int position) {
        return manual_events_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null)
            convertView = inflater.inflate(R.layout.layout_manual_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.manualName);
        TextView link = (TextView) convertView.findViewById(R.id.manualLink);

        // getting eventlist data for the row

        Manual event= manual_events_list.get(position);
        title.setText(event.getName());
        link.setText(event.getLink());

        return convertView;
    }

}
