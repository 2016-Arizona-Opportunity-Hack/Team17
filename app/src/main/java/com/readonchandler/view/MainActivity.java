package com.readonchandler.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.readonchandler.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import database.DBHelper;
import model.Manual;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected RelativeLayout manualLayout;
    protected RelativeLayout nearbyLayout;
    protected RelativeLayout calendarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.getInstance(this);
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

    public void saveDatabaseFile(){
        String DATABASE_PATH = "/data/data/"+"com.readonchandler"+"/databases/"+ DBHelper.DATABASE_NAME;
        String diskState = Environment.getExternalStorageState();
        if(diskState.equals(Environment.MEDIA_MOUNTED)) {
            File dbFile = new File(DATABASE_PATH);
            if(dbFile.exists()){
                try{
                    File backUpDB = new File(diskState+"/readonchandler/", "backup.db");
                    FileChannel src = new FileInputStream(dbFile).getChannel();
                    FileChannel dst = new FileOutputStream(backUpDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }catch (Exception e){

                }
            }

        }
    }
    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id){
            case R.id.manual_layout:
                Intent manualIntent = new Intent(this, Manuals.class);
                startActivity(manualIntent);
                break;

            case R.id.nearby_layout:
                saveDatabaseFile();
                Intent nearByIntent = new Intent(this, NearByActivity.class);
                startActivity(nearByIntent);
                break;

            case R.id.calendar_layout:
                startActivity(new Intent(this,CalendarActivty.class));
                break;

        }
    }
}
