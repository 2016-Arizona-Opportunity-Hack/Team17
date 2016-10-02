package control;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import database.DBContentProvider;
import database.DBHelper;
import model.Event;
import model.Manual;

/**
 * Created by "nithesh" on 10/2/2016.
 */

public class FbMessagingService extends FirebaseMessagingService {
    Event event;
    Manual manual;
    SQLiteDatabase db;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        String message = "";
        message = message+"From: " + remoteMessage.getFrom();
        DBHelper dbHelper = DBHelper.getInstance(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        // Check if message contains a data payload.

        //Added the parsing logic
        if (remoteMessage.getData().size() > 0) {
            message = message+"Message data payload: " + remoteMessage.getData();
            String string = remoteMessage.getData().toString();
            String [] result = string.split(",");
            int i = 0;
            if(result[i++].equals("Event")) {
                event.setName(result[i++]);
                event.setDate(result[i++]);
                event.setTime(result[i++]);
                event.setLatitude(Double.parseDouble(result[i++]));
                event.setLongitude(Double.parseDouble(result[i++]));
                event.setVideoLink(result[i++]);
                event.setEventDetails(result[i++]);

                DBContentProvider.insertEvent(db, event);
            }else{
                manual.setName(result[i++]);
                manual.setLink(result[i++]);
                DBContentProvider.insertManual(db,manual);
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            message = message+"Message Notification Body: " + remoteMessage.getNotification().getBody();
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
}
