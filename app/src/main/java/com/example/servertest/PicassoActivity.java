package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        ImageView iv = (ImageView)findViewById(R.id.iv);
        ImageView iv1 = (ImageView)findViewById(R.id.iv1);

        Picasso.get().load("https://square.github.io/picasso/static/sample.png").into(iv);
        Picasso.get().load("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").into(iv1);
    }
}