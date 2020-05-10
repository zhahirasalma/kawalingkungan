package com.example.kawalingkungan;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID=0;
    private static final String CHANNEL_ID="ch1";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotifyManager =(NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);

        Intent alarmIntent = new Intent(context, EarthquakeActivity.class);

        PendingIntent notificationPendingIntent=
                PendingIntent.getActivity(context, NOTIFICATION_ID,
                        alarmIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notifyBuilder
                =new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(notificationPendingIntent)
                .setContentTitle("Kawal Gempa Bumi")
                .setContentText("Cek apakah ada gempa bumi yang baru saja terjadi di sekitarmu")
                .setSmallIcon(R.drawable.logocoklat)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .addAction(R.drawable.logocoklat, "Buka",
                        notificationPendingIntent)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

            mNotifyManager.notify(100, notifyBuilder.build());

    }
}
