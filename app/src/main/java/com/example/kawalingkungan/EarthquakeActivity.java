package com.example.kawalingkungan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private EarthquakeAdapter earthquakeAdapter;
    private RecyclerView rvEarthquake;
    List<ModelInfo> listEarthquake =new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ModelInfo", "OnCreate()");
        setContentView(R.layout.activity_earthquake);

        rvEarthquake=findViewById(R.id.rvEarthquake);

        if(isNetworkAvailable()){
            Log.i("InfoGempa", "starting download task");
            SitesDownloadTask download=new SitesDownloadTask();
            download.execute();
        }else {
            earthquakeAdapter=new EarthquakeAdapter(getApplicationContext(), -1,
                    InfoXmlPullParser.getStackFromFile(EarthquakeActivity.this));
            rvEarthquake.setAdapter(earthquakeAdapter);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Downloader.DownloadFromUrl("https://data.bmkg.go.id/gempaterkini.xml",
                        openFileOutput("GempaTerkini.xml", Context.MODE_PRIVATE));
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            earthquakeAdapter=new EarthquakeAdapter(EarthquakeActivity.this, -1,
                    InfoXmlPullParser.getStackFromFile(EarthquakeActivity.this));
            rvEarthquake.setAdapter(earthquakeAdapter);
//            Log.i("InfoGempa", "adapter size = " + earthquakeAdapter.getItemCount());
        }
    }
}
