package com.readonchandler.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lucky.readonchandler.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected RelativeLayout manualLayout;
    protected RelativeLayout nearbyLayout;
    protected RelativeLayout calendarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manualLayout = (RelativeLayout) findViewById(R.id.manual_layout);
        nearbyLayout = (RelativeLayout) findViewById(R.id.nearby_layout);
        calendarLayout = (RelativeLayout) findViewById(R.id.calendar_layout);

        manualLayout.setOnClickListener(this);
        nearbyLayout.setOnClickListener(this);
        calendarLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.manual_layout:

                break;

            case R.id.nearby_layout:
                
                break;

            case R.id.calendar_layout:

                break;
        }
    }
}
