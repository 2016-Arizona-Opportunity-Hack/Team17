package com.readonchandler.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ListView;

import com.readonchandler.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class CalendarActivty extends AppCompatActivity {

    private static final String TAG = CalendarActivty.class.getName();
    private ListView listView;
    private CustomCalendarAdapter adapter;
    private List<EventsModel> eventList = new ArrayList<EventsModel>();
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_calendar);
        CalendarView mCalendar = (CalendarView) findViewById(R.id.calendarId);
        mCalendar.setFirstDayOfWeek(1);

        //TODO - DELETE temporary create data
        setEventsdata();

        Log.d(TAG,eventList+"");
        for (int i = 0; i < 5; i++) {
            Log.d(TAG,eventList.get(i).getTitle()+" "+eventList.get(i).getTime());
        }
        listView = (ListView) findViewById(R.id.eventsListView);



        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Log.d(TAG," selected :"+dayOfMonth);
                adapter = new CustomCalendarAdapter(mContext, eventList);
                listView.setAdapter(adapter);

            }
       });

    }

   //Temporary set dat in events  model
    public void setEventsdata()
    {
        for (int i = 0; i < 5; i++) {
            final EventsModel eventsModel = new EventsModel();
            eventsModel.setTitle("Gold "+i);
            eventsModel.setTime("12- 2 PM "+i);
            eventList.add( eventsModel );
        }

    }
}
