package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by "nithesh" on 10/1/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "readOndb";

    public static final int DATABASE_VERSION = 1;


    private Context context;

    private static DBHelper sInstance;

    public static synchronized DBHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private static final String COMMA_SEPARATOR = ",";
    private static final String OPEN_BRACE = " ( ";
    private static final String CLOSE_BRACE = " ) ";

    private static final String SQL_CREATE_EVENTS_TABLE = "CREATE TABLE "+DatabaseContract.Events.TABLE_NAME +
            OPEN_BRACE+
            DatabaseContract.Events._ID +" INTEGER PRIMARY KEY "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_NAME+" TEXT "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_DATE+" TEXT "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_LATITUDE+" REAL "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_LONGITUDE+" REAL "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_VIDEO_LINK+" TEXT "+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_EVENT_DETAILS+"TEXT"+COMMA_SEPARATOR+
            DatabaseContract.Events.COLUMN_NAME_TIME+" TEXT "+
            CLOSE_BRACE;

    private static final String SQL_CREATE_MANUAL_TABLE = "CREATE TABLE "+DatabaseContract.Manual.TABLE_NAME+
            OPEN_BRACE+
            DatabaseContract.Manual._ID +" INTEGER PRIMARY KEY "+COMMA_SEPARATOR+
            DatabaseContract.Manual.COLUMN_NAME_NAME+" TEXT "+COMMA_SEPARATOR+
            DatabaseContract.Manual.COLUMN_NAME_LINK+" TEXT "+
            CLOSE_BRACE;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_EVENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MANUAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
