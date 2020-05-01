package com.example.kawalingkungan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

    String value_died, value_cured, value_pos, ind_pos, ind_cured, ind_died;

    private RequestQueue mQueue;

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

        mQueue= Volley.newRequestQueue(this);

        json_died();
        json_cured();
        json_pos();
        json_ina();
    }

    private void json_died(){
        String url="https://api.kawalcorona.com/meninggal";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("_try", response.toString());
                    value_died=response.getString("value");

                    global_died.setText(value_died);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void json_cured(){
        String url="https://api.kawalcorona.com/sembuh";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("_try", response.toString());
                    value_cured=response.getString("value");

                    global_cured.setText(value_cured);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void json_pos(){
        String url="https://api.kawalcorona.com/positif";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("_try", response.toString());
                    value_pos=response.getString("value");

                    global_pos.setText(value_pos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void json_ina(){
        String url="https://api.kawalcorona.com/indonesia/";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("data", response.toString());
                    ind_pos=response.getString("positif");
                    ind_cured=response.getString("sembuh");
                    ind_died=response.getString("meninggal");

                    ina_pos.setText(ind_pos);
                    ina_cured.setText(ind_cured);
                    ina_died.setText(ind_died);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

}
