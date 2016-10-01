package com.readonchandler.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import com.readonchandler.R;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class CalendarActivty extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_calendar);
        CalendarView mCalendar = (CalendarView) findViewById(R.id.calendarId);
        mCalendar.setFirstDayOfWeek(1);
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            }
        });


    }
}
