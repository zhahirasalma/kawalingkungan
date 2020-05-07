package com.example.kawalingkungan;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Implementation of App Widget functionality.
 */
public class CovidWidget extends AppWidgetProvider {
    private static final String SHARED_PREF_FILE=
            "com.example.kawalingkungan1";

    TextView ina_pos;
    TextView ina_cured;
    TextView ina_died;
    String ind_pos, ind_cured, ind_died;

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        ina_pos= ina_pos.findViewById(R.id.ina_pos_amount);
        ina_cured= ina_cured.findViewById(R.id.ina_cured_amount);
        ina_died=ina_died.findViewById(R.id.ina_died_amount);

        load_ina();


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.covid_widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intentUpdate=new Intent(context, KawalCovid.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] idArray=new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        PendingIntent pendingUpdate= PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intent=new Intent(context, KawalCovid.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,
                0, intent, 0);

        views.setOnClickPendingIntent(R.id.buttonWidget, pendingIntent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        for (int appWidgetId : appWidgetIds) {
            Intent intent=new Intent(context, KawalCovid.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0,
                    intent, 0);

            RemoteViews views=new RemoteViews(context.getPackageName(), R.layout.covid_widget);
//            views.setTextViewText(R.id.ina_pos, );
            views.setOnClickPendingIntent(R.id.buttonWidget, pendingIntent);


            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void load_ina(){
        AndroidNetworking.get("https://api.kawalcorona.com/indonesia/")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("lokal", response.toString());
                        try {

                            for(int i=0;i<response.length();i++){
                                JSONObject nasional=response.getJSONObject(i);
                                ind_pos=nasional.getString("positif");
                                ind_cured=nasional.getString("sembuh");
                                ind_died=nasional.getString("meninggal");

                                ina_pos.setText(ind_pos);
                                ina_cured.setText(ind_cured);
                                ina_died.setText(ind_died);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}

