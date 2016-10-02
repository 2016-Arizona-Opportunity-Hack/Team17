package com.readonchandler.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.readonchandler.R;

import java.util.ArrayList;
import java.util.List;

import database.DBContentProvider;
import database.DBHelper;
import database.DatabaseContract;
import model.Event;
import model.Manual;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class Manuals extends AppCompatActivity {
    Manual manualModel = new Manual();
    private ManualAdapter adapter;
    private Context mContext;
    private ListView listView;
    List<Manual> manualList = new ArrayList<Manual>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_manual);

        mContext = this;
        listView = (ListView) findViewById(R.id.manualListView);
        adapter = new ManualAdapter(mContext, DBContentProvider.getAllManuals(DBHelper.getInstance(this).getReadableDatabase()));
        listView.setAdapter(adapter);

    }
}

