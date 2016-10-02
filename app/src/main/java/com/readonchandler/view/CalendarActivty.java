package com.readonchandler.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ListView;

import com.readonchandler.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DBContentProvider;
import database.DBHelper;
import database.DatabaseContract;
import model.Event;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class CalendarActivty extends AppCompatActivity {

    private static final String TAG = CalendarActivty.class.getName();
    private ListView listView;
    private CustomCalendarAdapter adapter;
    private Context mContext;
    Event eventsModel = new Event();

    private List<Event> eventsList;
    List<Event> adapterevents = new ArrayList<Event>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_calendar);
        CalendarView mCalendar = (CalendarView) findViewById(R.id.calendarId);
        mCalendar.setFirstDayOfWeek(1);
        DBHelper helper = DBHelper.getInstance(mContext);
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        eventsList = DBContentProvider.getAllEvents(sqLiteDatabase);
        //TODO - DELETE temporary create data
        Date cDate = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cDate);
        listView = (ListView) findViewById(R.id.eventsListView);
        adapterevents = new ArrayList<Event>();
        for(Event event : eventsList){
            if(event.getDate().equals(date)){
                adapterevents.add(event);
            }
        }

        adapter = new CustomCalendarAdapter(mContext, adapterevents);
        listView.setAdapter(adapter);

        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Log.d(TAG," selected :"+dayOfMonth);
                month = month+1;
                String date = year+"-"+month+"-"+dayOfMonth;
                adapterevents = new ArrayList<Event>();
                for(Event event : eventsList){
                    String eventDate = event.getDate();
                    eventDate = eventDate.replace(" ", "");
                    if(eventDate.equals(date)){
                        adapterevents.add(event);
                    }
                }
                adapter.events_list = adapterevents;
                //listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
       });

    }


   //Temporary set dat in events  model
//    public List<Event> getEventsdata(String date)
//    {
//        List<Event> eventList = new ArrayList<Event>();
//                Cursor cursor = sqLiteDatabase.query(DatabaseContract.Events.TABLE_NAME, new String[]{}, date, null, null, null, null);
//        if(cursor != null){
//            cursor.moveToFirst();
//            while(cursor.moveToNext()){
//                eventsModel.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_NAME)));
//                eventsModel.setDate(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_DATE)));
//                eventsModel.setLatitude(cursor.getDouble(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_LATITUDE)));
//                eventsModel.setLongitude(cursor.getDouble(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_LONGITUDE)));
//                eventsModel.setTime(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_TIME)));
//                eventsModel.setEventDetails(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_EVENT_DETAILS)));
//                eventsModel.setVideoLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_VIDEO_LINK)));
//                eventList.add(eventsModel);
//            }
//        }
//        return eventList;
//    }
}
