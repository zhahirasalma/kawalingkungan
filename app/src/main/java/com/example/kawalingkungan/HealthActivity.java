package com.example.kawalingkungan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity implements NewsAdapter.onSelectData {

    RecyclerView rvHealth;
    NewsAdapter newsAdapter;
    List<ModelNews> listNews = new ArrayList<>();
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.dialog_tunggu));
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.dialog_tunggu_2));
        newsAdapter = new NewsAdapter(this, listNews,this);
        loadJSON();

        rvHealth = findViewById(R.id.rvNews);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvHealth.setLayoutManager(manager);
        rvHealth.setAdapter(newsAdapter);
        rvHealth.setHasFixedSize(true);
        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbNews);
        toolbar.setTitle("Kawal Kesehatan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadJSON() {
        progressDialog.show();
        AndroidNetworking.get(NewsApi.GET_CATEGORY_HEALTH)
                .setTag("Test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("articles");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                ModelNews dataApi = new ModelNews();
                                Log.d("news", playerArray.get(i).toString());
                                String title = temp.getString("title");

//                                Log.d("Newsresponse",title);

                                dataApi.setTitle(title);
                                dataApi.setUrl(temp.getString("url"));
                                dataApi.setPublishedAt(temp.getString("publishedAt"));
                                dataApi.setUrlToImage(temp.getString("urlToImage"));

                                listNews.add(dataApi);
                                newsAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HealthActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(HealthActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void showNews() {
        newsAdapter = new NewsAdapter(HealthActivity.this, listNews, this);
        rvHealth.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(ModelNews mdlNews) {
        Intent intent = new Intent(HealthActivity.this, OpenNewsActivity.class);
        intent.putExtra("url", mdlNews.getUrl());
        startActivity(intent);
    }

}

