package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pict.acm.pulzion18.EventActivity;
import com.pict.acm.pulzion18.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Explore(View view){
        Intent i = new Intent(HomeActivity.this,EventActivity.class);
        startActivity(i);
    }

    public void facebook(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("https://www.facebook.com/acmpict/"));
        startActivity(intent);
    }
    public void instagram(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("https://www.instagram.com/acm.pict/?hl=en"));
        startActivity(intent);
    }

    public void website(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("http://pict.acm.org/pulzion17.html"));
        startActivity(intent);
    }

}
