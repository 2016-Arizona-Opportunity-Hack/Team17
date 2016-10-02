package database;

import android.provider.BaseColumns;

/**
 * Created by "Lakshmisagar" on 10/1/2016.
 */

public final class DatabaseContract {

    public DatabaseContract(){


    }

    public abstract class Manual implements BaseColumns {
        public static final String TABLE_NAME = "manual";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LINK = "link";
    }

    public abstract class Events implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_VIDEO_LINK = "video_link";
        public static final String COLUMN_NAME_EVENT_DETAILS = "event_details";
        public static final String COLUMN_NAME_TIME = "time";

    }
}
