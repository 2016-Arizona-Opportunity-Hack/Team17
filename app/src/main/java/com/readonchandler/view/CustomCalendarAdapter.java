package com.readonchandler.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.readonchandler.R;

import java.util.List;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class CustomCalendarAdapter extends BaseAdapter {
    private static final String TAG = CustomCalendarAdapter.class.getName();
    Context mContext;
    LayoutInflater inflater;
    private List<EventsModel> events_list;

    public CustomCalendarAdapter(Context context, List<EventsModel> events) {
        mContext = context;
        events_list = events;
    }

    @Override
    public int getCount() {
        return events_list.size();
    }

    @Override
    public Object getItem(int position) {
        return events_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG,"getView");
        if (inflater == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null)
            convertView = inflater.inflate(R.layout.calendar_day_list_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.eventTitle);
        TextView time = (TextView) convertView.findViewById(R.id.eventTime);

        // getting eventlist data for the row

        EventsModel m = events_list.get(position);
        title.setText(m.getTitle());
        time.setText(m.getTime());

        return convertView;
    }

}
