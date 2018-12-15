package com.example.c052735.simplephotoview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("photoUrl");
        ImageView imageView = findViewById(R.id.details);
        Glide.with(this).load(url).into(imageView);
    }
}
