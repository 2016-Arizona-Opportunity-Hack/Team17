package com.readonchandler.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.readonchandler.view.readonchandler.R;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.calendar_view);
        Calendar mCalendar = new Calendar();

    }
}
