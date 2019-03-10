package com.example.betulsenoglu.thechef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class tarifDetay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif_detay);

        TextView baslik = findViewById(R.id.tarifDetayBaslik);
        TextView aciklama = findViewById(R.id.tarifDetayAciklama);
        String imglink;
        Intent a = getIntent();

        baslik.setText(a.getStringExtra("baslik"));
        aciklama.setText(a.getStringExtra("aciklama"));
        imglink = a.getStringExtra("image");

        ImageView ivBasicImage = (ImageView) findViewById(R.id.tarifDetayFoto);
        Picasso.with(getApplicationContext()).load(imglink).into(ivBasicImage);

    }

    public void dismissOnClick(View v){
        finish();
    }
}
