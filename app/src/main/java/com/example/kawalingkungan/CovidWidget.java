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
import com.androidnetworking.interfaces.JSONObjectRequestListener;

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
//
//    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                int appWidgetId) {
//
//
//        ina_pos= ina_pos.findViewById(R.id.ina_pos_amount);
//        ina_cured= ina_cured.findViewById(R.id.ina_cured_amount);
//        ina_died=ina_died.findViewById(R.id.ina_died_amount);
//
//        load_ina();
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        ina_pos= ina_pos.findViewById(R.id.ina_pos_amount);
//        ina_cured= ina_cured.findViewById(R.id.ina_cured_amount);
//        ina_died=ina_died.findViewById(R.id.ina_died_amount);

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        for (int appWidgetId : appWidgetIds) {
            Intent intent=new Intent(context, KawalCovid.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context, 0,
                    intent, 0);

            Intent viewIntent=new Intent(context, CovidWidget.class);
            PendingIntent viewPendingIntent=PendingIntent.getActivity(context, 0,
                    viewIntent, 0);

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
//
//    private void lokal(){
//        AndroidNetworking.get("https://api.covid19api.com/summary")
//                .setTag("test")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("indonesia", response.toString());
//                            JSONArray array = response.getJSONArray("Countries");
//                            for(int i=0; i<array.length();i++){
//                                JSONObject jsonObject=array.getJSONObject(i);
//                                String ind = jsonObject.getString("Country");
//                                if(ind.equals("Indonesia")){
//
//                                    ind_pos=jsonObject.getString("TotalConfirmed");
//                                    ind_cured=jsonObject.getString("TotalRecovered");
//                                    ind_died=jsonObject.getString("TotalDeaths");
//
//
//                                    ina_pos.setText(ind_pos);
//                                    ina_cured.setText(ind_cured);
//                                    ina_died.setText(ind_died);
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });
//    }
}

