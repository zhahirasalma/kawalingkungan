package com.example.kawalingkungan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KawalCovid extends AppCompatActivity {
    TextView ina_pos;
    TextView ina_cured;
    TextView ina_died;
    TextView global_pos;
    TextView global_cured;
    TextView global_died;

    String value_died, value_cured, value_pos, ind_pos, ind_cured, ind_died;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kawal_covid);
        ina_pos=findViewById(R.id.ina_pos_amount);
        ina_cured=findViewById(R.id.ina_cured_amount);
        ina_died=findViewById(R.id.ina_died_amount);
        global_pos=findViewById(R.id.global_pos_amount);
        global_cured=findViewById(R.id.global_cured_amount);
        global_died=findViewById(R.id.global_died_amount);

        global();
        lokal();
        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbCov);
        toolbar.setTitle("Kawal Covid");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void global(){
        AndroidNetworking.get("https://api.covid19api.com/summary")
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("positif", response.toString());
                            JSONObject jsonObject=response.getJSONObject("Global");
                                value_pos = jsonObject.getString("TotalConfirmed");
                                value_cured = jsonObject.getString("TotalRecovered");
                                value_died = jsonObject.getString("TotalDeaths");

                                global_pos.setText(value_pos + getString(R.string.kasus));
                                global_cured.setText(value_cured + getString(R.string.kasus));
                                global_died.setText(value_died + getString(R.string.kasus));

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(KawalCovid.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(KawalCovid.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void lokal(){
        AndroidNetworking.get("https://api.covid19api.com/summary")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("indonesia", response.toString());
                            JSONArray array = response.getJSONArray("Countries");
                            for(int i=0; i<array.length();i++){
                                JSONObject jsonObject=array.getJSONObject(i);
                                String ind = jsonObject.getString("Country");
                                if(ind.equals("Indonesia")){

                                    ind_pos=jsonObject.getString("TotalConfirmed");
                                    ind_cured=jsonObject.getString("TotalRecovered");
                                    ind_died=jsonObject.getString("TotalDeaths");

                                    ina_pos.setText(ind_pos + getString(R.string.kasus));
                                    ina_cured.setText(ind_cured + getString(R.string.kasus));
                                    ina_died.setText(ind_died + getString(R.string.kasus));

                                    sharedPreferences = getSharedPreferences("VALUE", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("INA_POS", ind_pos);
                                    editor.putString("INA_SEMBUH", ind_cured);
                                    editor.putString("INA_WAFAT", ind_died);
                                    editor.apply();
                                    Log.d("covid_widget_b", sharedPreferences.getString("INA_POS", "-"));

                                    setData();

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(KawalCovid.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setData(){
        SharedPreferences sharedPreferences= this.getSharedPreferences("VALUE",
                Context.MODE_PRIVATE);


    }


}
