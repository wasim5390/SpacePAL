package com.roadhourse.spacepal.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.roadhourse.spacepal.R;


/**
 * Created by sidhu on 4/19/2018.
 */

public class NotificationUtil {

    private static final String TAG = "NotificationUtil";

    public static void create(Context context, int id, Intent intent, int smallIcon, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"DirectMail")
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true);

        // Build a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification sent successfully");
    }

    public static void createStackNotification(Context context, int id,String groupId, Intent intent,int smallIcon, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Intent to broadcast
        PendingIntent p = intent != null ? PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT) : null;

        // create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"DirectMail")
                .setContentIntent(p)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setGroup(groupId)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(id, n);

        Log.d(TAG,"Notification sent successfully");
    }


    public static void create(Context context, int smallIcon, String contentTitle, String contentText) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Cria a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"DirectMail")
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setColor(ContextCompat.getColor(context, R.color.blue))
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(smallIcon)
                .setAutoCancel(true);

        // Dispara a notification
        Notification n = builder.build();
        manager.notify(0, n);

        Log.d(TAG,"Notification sent successfully");
    }

    public static void cancel(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancelAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
