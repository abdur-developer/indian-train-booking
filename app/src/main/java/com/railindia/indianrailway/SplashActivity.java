package com.railindia.indianrailway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView textView,textVieww;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //load ads
        textView = findViewById(R.id.textView);
        textVieww = findViewById(R.id.textVieww);

        Animation slideLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        textView.startAnimation(slideLeft);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, Home.class));
            finish();
        },4000);
    }
}