package com.example.kawalingkungan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmHandler {
    private Context context;
    private static Uri alarmSound;

    public AlarmHandler(Context context){
        this.context=context;
    }

    public void setAlarmManager(){
        Intent intent=new Intent(context, AlarmReceiver.class);
        PendingIntent notifPendingIntent= PendingIntent.getBroadcast(context, 2, intent,
                0);
        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(alarmManager!=null) {
            long triggerAfter = 1 * 60 * 1000;
            long triggerEvery = 720 * 60 * 1000;
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAfter,
                    triggerEvery,
                    notifPendingIntent);
            alarmSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

    }

    public void cancelAlarmManager(){
        Intent intent=new Intent(context, AlarmReceiver.class);
        PendingIntent cancelPendingIntent=PendingIntent.getBroadcast(context, 2,
                intent, 0);
        AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager!=null){
            alarmManager.cancel(cancelPendingIntent);
        }

    }
}
