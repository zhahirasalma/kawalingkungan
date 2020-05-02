package com.example.kawalingkungan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeViewHolder> {
    public List<ModelInfo> infoList;
    private Context mContext;
    private EarthquakeAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelInfo mdlInfo);
    }

    public EarthquakeAdapter(Context context, int android,
                             List<ModelInfo> onSelectData){
        this.mContext=context;
//        this.infoList=android;
//        this.onSelectData=onSelectData;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_gempa, parent, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        final ModelInfo gempa = infoList.get(position);

        Glide.with(mContext)
                .load(gempa.get_symbol())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_black_24dp)
                //.transform(new CenterInside(), new RoundedCorners(30))
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imgGempa);

        Log.d("adapterOnBind", gempa.getTanggal());

        holder.tanggal.setText(gempa.getTanggal());
        holder.jam.setText(TimeUnits.getTimeAgo(gempa.getJam()));
        holder.magnitude.setText(gempa.getMagnitude());
        holder.wilayah.setText(gempa.getWilayah());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(gempa);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return infoList.size();
        return 0;
    }
}
