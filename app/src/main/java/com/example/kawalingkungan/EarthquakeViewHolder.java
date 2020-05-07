package com.example.kawalingkungan;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
//    public ImageView imgGempa;
    public WebView imgGempa;
    public TextView tanggal;
    public TextView jam;
    public TextView magnitude;
    public TextView wilayah, lintang, bujur, kedalaman;
    public View view;

    public EarthquakeViewHolder(View view){
        super(view);

        tanggal=view.findViewById(R.id.tanggalGempa);
        jam=view.findViewById(R.id.jamGempa);
        magnitude=view.findViewById(R.id.magGempa);
        lintang=view.findViewById(R.id.LinGempa);
        bujur=view.findViewById(R.id.BuGempa);
        kedalaman=view.findViewById(R.id.KedGempa);
        wilayah=view.findViewById(R.id.wilGempa);
//        imgGempa=view.findViewById(R.id.imgGempa);
        this.view=view;
    }

}
