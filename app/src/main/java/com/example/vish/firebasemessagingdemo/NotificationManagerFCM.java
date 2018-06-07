package com.example.vish.firebasemessagingdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by admin on 26/3/2018.
 */

public class NotificationManagerFCM {

    private Context mCtx;
    private static NotificationManagerFCM mInstance;

    private NotificationManagerFCM(Context context) {
        mCtx = context;
    }

    public static synchronized NotificationManagerFCM getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotificationManagerFCM(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(body);


        Intent resultIntent = new Intent(mCtx, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
