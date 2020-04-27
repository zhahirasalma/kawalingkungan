package com.example.kawalingkungan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
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

        loadJSON();
    }

    private void loadJSON(){
        AndroidNetworking.get("https://api.kawalcorona.com/indonesia/")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("_DATA", response.toString());

                        
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
