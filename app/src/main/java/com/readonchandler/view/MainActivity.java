package com.readonchandler.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.readonchandler.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected RelativeLayout manualLayout;
    protected RelativeLayout nearbyLayout;
    protected RelativeLayout calendarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle data = getIntent().getExtras();
        manualLayout = (RelativeLayout) findViewById(R.id.manual_layout);
        nearbyLayout = (RelativeLayout) findViewById(R.id.nearby_layout);
        calendarLayout = (RelativeLayout) findViewById(R.id.calendar_layout);

        manualLayout.setOnClickListener(this);
        nearbyLayout.setOnClickListener(this);
        calendarLayout.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle data = intent.getExtras();
        String something = "something log value";
        Log.d("something", something);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.manual_layout:
                Intent manualIntent = new Intent(this, DetailActivty.class);
                startActivity(manualIntent);
                break;

            case R.id.nearby_layout:
                Intent nearByIntent = new Intent(this, NearByActivity.class);
                startActivity(nearByIntent);
                break;

            case R.id.calendar_layout:
                startActivity(new Intent(this,CalendarActivty.class));
                break;

        }
    }
}
