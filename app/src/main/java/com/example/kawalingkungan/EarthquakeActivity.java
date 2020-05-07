package com.example.kawalingkungan;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements EarthquakeAdapter.onSelectData{

//    private RecyclerView rvEarthquake;
    List<ModelInfo> listEarthquake =new ArrayList<>();
    EarthquakeAdapter earthquakeAdapter;
    RecyclerView rvEarthquake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ModelInfo", "OnCreate()");
        setContentView(R.layout.activity_earthquake);

        rvEarthquake=(RecyclerView) findViewById(R.id.rvEarthquake);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvEarthquake.setLayoutManager(manager);
        rvEarthquake.setAdapter(earthquakeAdapter);
        rvEarthquake.setHasFixedSize(true);

        if(isNetworkAvailable()){
            Log.i("InfoGempa", "starting download task");
            SitesDownloadTask download=new SitesDownloadTask(this);
            download.execute();
        }else {
            earthquakeAdapter = new EarthquakeAdapter(getApplicationContext(), -1,
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

    @Override
    public void onSelected(ModelInfo mdlInfo) {

    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, List<ModelInfo>> {
        Context context;
        SitesDownloadTask(Context context){
            this.context = context;
        }

        @Override
        protected List<ModelInfo> doInBackground(Void... voids) {
            byte data[] = new byte[1024];
            try {
                data = Downloader.DownloadFromUrl("https://data.bmkg.go.id/gempaterkini.xml",
                        openFileOutput("GempaTerkini.xml", Context.MODE_PRIVATE));

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            Log.d("Data",data.toString());
            return listEarthquake;
        }

        @Override
        protected void onPostExecute(List<ModelInfo> aVoid) {

            earthquakeAdapter=new EarthquakeAdapter(EarthquakeActivity.this, -1,
                    InfoXmlPullParser.getStackFromFile(EarthquakeActivity.this));
            rvEarthquake.setAdapter(earthquakeAdapter);

            listEarthquake = InfoXmlPullParser.getStackFromFile(context);
            StringBuilder builder = new StringBuilder();
            for (int i=0;i<listEarthquake.size();i++){
                Log.d("InfoListGempa",listEarthquake.get(i).toString());
//                ModelInfo data = new ModelInfo();
//                StringBuilder jam = builder.append(data.jam);
//                data.setJam(jam.toString());

//                String jam = String.valueOf(builder.append(data.jam));
//                data.setJam(jam);

//                listEarthquake.add(data);
                earthquakeAdapter.notifyDataSetChanged();

            }
        }
    }



}
