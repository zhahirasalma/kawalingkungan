package com.example.kawalingkungan;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView img_kawalKes, img_kawalCov, img_kawalLing, img_kawalGem;
    private TextView bahasa;
    TextView abaikan;

    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String NOTIF_URL = "https://google.com";
    private static final String CHANNEL_ID = "ch1";
    private static final String ACTION_UPDATE_NOTIFICAION =
            "com.example.notificationsapp.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICAION =
            "com.example.notificationsapp.ACTION_CANCEL_NOTIFICATION";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_kawalKes = findViewById(R.id.img_kawalKesehatan);
        img_kawalLing = findViewById(R.id.img_kawalLingkungan);
        img_kawalGem = findViewById(R.id.img_kawalGempa);
        img_kawalCov = findViewById(R.id.img_kawalCovid);
        bahasa = findViewById(R.id.ganti_bahasa);
        abaikan = findViewById(R.id.abaikan_text);

        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        createNotificationChannel();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_CANCEL_NOTIFICAION);
//        registerReceiver(mReceiver, intentFilter);

        AlarmHandler alarmHandler=new AlarmHandler(this);
        alarmHandler.setAlarmManager();
        abaikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apakah anda yakin akan mengabaikan notifikasi?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alarmHandler.cancelAlarmManager();
                            }
                        }).setNegativeButton("Tidak", null);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

        img_kawalKes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });
        img_kawalLing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnvironmentActivity.class);
                startActivity(intent);
            }
        });
        img_kawalGem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EarthquakeActivity.class);
                startActivity(intent);
            }
        });
        img_kawalCov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KawalCovid.class);
                startActivity(intent);
            }
        });
        bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent languageIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(languageIntent);
            }
        });



    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "Description..";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

//    private void sendNotification() {
//        Intent notificationIntent = new Intent(this, EarthquakeActivity.class);
//        PendingIntent notificationPendingIntent =
//                PendingIntent.getActivity(this, NOTIFICATION_ID,
//                        notificationIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICAION);
//        PendingIntent cancelPendingIntent=PendingIntent.getActivity(
//                this, NOTIFICATION_ID, cancelIntent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.Builder notifyBuilder
//                = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle(getString(R.string.title_notif))
//                .setContentText(getString(R.string.subtitle_notif))
//                .setSmallIcon(R.drawable.logocoklat)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .addAction(R.drawable.logocoklat, "Buka",
//                        notificationPendingIntent)
////                .addAction(R.drawable.logocoklat, "Abaikan",
////                        cancelPendingIntent)
//                .setContentIntent(notificationPendingIntent)
//                .setAutoCancel(true);
//
//        mNotifyManager.notify(100, notifyBuilder.build());
//
////        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
////        long timeAtClick=System.currentTimeMillis();
////        long time2=10000;
////        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtClick+time2, cancelPendingIntent);
//    }

    private void stopNotification(){
        mNotifyManager.cancel(NOTIFICATION_ID);
    }
//
//    private class NotificationReceiver extends BroadcastReceiver {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            switch (action) {
//                case ACTION_CANCEL_NOTIFICAION:
//                    stopNotification();
//                    break;
//                case ACTION_UPDATE_NOTIFICAION:
//                    sendNotification();
//                    break;
//            }
//        }
//    }


}


