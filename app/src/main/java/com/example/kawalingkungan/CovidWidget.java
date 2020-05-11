package com.example.kawalingkungan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class CovidWidget extends AppWidgetProvider {
    private String sharedPositif;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                 int appWidgetId) {



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetId) {
        // There may be multiple widgets active, so update all of them


        CharSequence widgetText = context.getString(R.string.appwidget_text);
//        for (int appWidgetId : appWidgetIds) {
            Intent intent=new Intent(context, KawalCovid.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0,
                    intent, 0);

            RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.covid_widget);

            SharedPreferences sharedPreferences=context.getSharedPreferences(
                    "VALUE", Context.MODE_PRIVATE);

            Log.d("value_widget", sharedPreferences.getString("INA_POS", "-"));

            views.setOnClickPendingIntent(R.id.buttonWidget, pendingIntent);
            views.setTextViewText(R.id.textPos, sharedPreferences.getString("INA_POS", "-"));
            views.setTextViewText(R.id.textSem, sharedPreferences.getString("INA_SEMBUH", "-"));
            views.setTextViewText(R.id.textWaf, sharedPreferences.getString("INA_WAFAT", "-"));

            Intent viewIntent=new Intent(context, CovidWidget.class);
            PendingIntent viewPendingIntent=PendingIntent.getActivity(context, 0,
                    viewIntent, 0);
            viewIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);


            appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

