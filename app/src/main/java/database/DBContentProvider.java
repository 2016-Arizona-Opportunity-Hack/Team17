package database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Manual;

/**
 * Created by "nithesh" on 10/2/2016.
 */

public class DBContentProvider {

    public static synchronized boolean insertEvent(SQLiteDatabase sqLiteDatabase, Event event){
        if(event == null){
            return false;
        }

        ContentValues contentValues = new ContentValues();
        if(event.getName() != null){
            contentValues.put(DatabaseContract.Events.COLUMN_NAME_NAME, event.getName());
        }

        if(event.getDate() != null){
            contentValues.put(DatabaseContract.Events.COLUMN_NAME_DATE, event.getDate());
        }

        contentValues.put(DatabaseContract.Events.COLUMN_NAME_LATITUDE, event.getLatitude());

        contentValues.put(DatabaseContract.Events.COLUMN_NAME_LONGITUDE, event.getLongitude());

        if(event.getVideoLink() != null){
            contentValues.put(DatabaseContract.Events.COLUMN_NAME_VIDEO_LINK, event.getVideoLink());
        }

        if(event.getTime() != null){
            contentValues.put(DatabaseContract.Events.COLUMN_NAME_TIME, event.getTime());
        }

        if(event.getEventDetails() != null){
            contentValues.put(DatabaseContract.Events.COLUMN_NAME_EVENT_DETAILS, event.getEventDetails());
        }

        return sqLiteDatabase.insert(DatabaseContract.Events.TABLE_NAME, null, contentValues) > 0 ? true : false ;
    }


    public static synchronized boolean insertManual(SQLiteDatabase sqLiteDatabase, Manual manual){
        if(manual == null){
            return false;
        }

        ContentValues contentValues = new ContentValues();
        if(manual.getName() != null){
            contentValues.put(DatabaseContract.Manual.COLUMN_NAME_NAME, manual.getName());
        }

        if(manual.getLink() != null){
            contentValues.put(DatabaseContract.Manual.COLUMN_NAME_LINK, manual.getLink());
        }
        if(contentValues.size() > 0){
            return sqLiteDatabase.insert(DatabaseContract.Manual.TABLE_NAME, null, contentValues) > 0 ? true : false ;
        } else {
            return false;
        }

    }

    public static synchronized List<Event> getAllEvents(SQLiteDatabase sqLiteDatabase){
        List<Event> events = new ArrayList<Event>();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.Events.TABLE_NAME, new String[]{}, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                Event event = new Event();
                event.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_NAME)));
                event.setDate(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_DATE)));
                event.setLatitude(cursor.getDouble(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_LATITUDE)));
                event.setLongitude(cursor.getDouble(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_LONGITUDE)));
                event.setTime(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_TIME)));
                event.setEventDetails(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_EVENT_DETAILS)));
                event.setVideoLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.Events.COLUMN_NAME_VIDEO_LINK)));
                events.add(event);
            }

        }

        return events;
    }

    public static synchronized List<Manual> getAllManuals(SQLiteDatabase sqLiteDatabase){
        List<Manual> manuals = new ArrayList<Manual>();

        Cursor cursor = sqLiteDatabase.query(DatabaseContract.Manual.TABLE_NAME, new String[]{}, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                Manual manual = new Manual();
                manual.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Manual.COLUMN_NAME_NAME)));
                manual.setLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.Manual.COLUMN_NAME_LINK)));
                manuals.add(manual);
            }

        }

        return manuals;

    }

    }
