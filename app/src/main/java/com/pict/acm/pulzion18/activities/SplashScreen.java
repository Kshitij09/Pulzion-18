package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.pict.acm.pulzion18.R;

import static com.pict.acm.pulzion18.R.layout.activity_splash_screen;

public class SplashScreen extends AppCompatActivity {

    public static int time_out = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen);

        ImageView pimage = findViewById(R.id.pulzionImage);
        pimage.animate().alpha(1f).setDuration(2000);

        ImageView rimage = findViewById(R.id.rocketImage);
        rimage.animate().translationY(-600).setDuration(1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        },time_out);
    }
}
