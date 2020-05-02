package com.example.kawalingkungan;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EarthquakeViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgGempa;
    public TextView tanggal;
    public TextView jam;
    public TextView magnitude;
    public TextView wilayah;
    public View view;

    public EarthquakeViewHolder(View view){
        super(view);

        imgGempa=view.findViewById(R.id.imgGempa);
        tanggal=view.findViewById(R.id.tanggalGempa);
        jam=view.findViewById(R.id.jamGempa);
        magnitude=view.findViewById(R.id.magGempa);
        wilayah=view.findViewById(R.id.wilGempa);
        this.view=view;
    }

}
