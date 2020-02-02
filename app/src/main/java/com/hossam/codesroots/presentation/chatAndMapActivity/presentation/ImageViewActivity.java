package com.hossam.codesroots.presentation.chatAndMapActivity.presentation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hossam.codesroots.delivery24.R;

public class ImageViewActivity extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        image = findViewById(R.id.image);

        String url =  getIntent().getStringExtra("imageUrl");
        Glide.with(this).load(url).into(image);
    }
}
