package com.example.kawalingkungan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img_kawalKes, img_kawalCov, img_kawalLing, img_kawalGem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_kawalKes=findViewById(R.id.img_kawalKesehatan);
        img_kawalLing=findViewById(R.id.img_kawalLingkungan);
        img_kawalGem=findViewById(R.id.img_kawalGempa);
        img_kawalCov=findViewById(R.id.img_kawalCovid);

        img_kawalKes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });
        img_kawalLing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, EnvironmentActivity.class);
                startActivity(intent);
            }
        });
//        img_kawalGem.setOnClickListener(this);
        img_kawalCov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, KawalCovid.class);
                startActivity(intent);
            }
        });

    }

}
