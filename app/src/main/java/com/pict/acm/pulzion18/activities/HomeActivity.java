package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.pict.acm.pulzion18.Constants.PULZION.SLIDES;

public class HomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Query slidesRef = rootRef.child(SLIDES).orderByKey();
        viewPager = findViewById(R.id.viewPager);
        viewPager.setVisibility(View.INVISIBLE);
        images = new ArrayList<>();
        slidesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap :
                        dataSnapshot.getChildren()) {
                    images.add(snap.getValue().toString());
                }
                viewPager.setVisibility(View.VISIBLE);
                adapter = new ViewPagerAdapter(HomeActivity.this, images);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*String[] images = {
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide1.png?alt=media&token=d7296655-0108-4b3f-9eff-cd9c22ce6e0d",
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide2.jpg?alt=media&token=8d3b00a0-a9cc-469d-9128-ea82249a6d45",
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide3.jpg?alt=media&token=f6fd9eb9-0c6d-4f4d-8993-d43fe34f30a6",
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide4.jpg?alt=media&token=3cdc5d3d-1bf7-4e3a-9ab0-1bf8964929c6",
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide5.jpg?alt=media&token=4a7815b6-d290-42d1-88be-ef1be6178f1f",
                "https://firebasestorage.googleapis.com/v0/b/pulzion-18.appspot.com/o/slide6.jpg?alt=media&token=b29eb1a5-4708-4737-8940-12de657f8530"
        };*/

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 5000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (HomeActivity.this.viewPager.getCurrentItem() == 0) {
                        HomeActivity.this.viewPager.setCurrentItem(1);
                    } else if (HomeActivity.this.viewPager.getCurrentItem() == 1) {
                        HomeActivity.this.viewPager.setCurrentItem(2);
                    } else if (HomeActivity.this.viewPager.getCurrentItem() == 2) {
                        HomeActivity.this.viewPager.setCurrentItem(3);
                    } else if (HomeActivity.this.viewPager.getCurrentItem() == 3) {
                        HomeActivity.this.viewPager.setCurrentItem(4);
                    } else {
                        HomeActivity.this.viewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }
    public void Explore(View view){
        Intent i = new Intent(HomeActivity.this,EventActivity.class);
        startActivity(i);
        finish();
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
        intent.setData(Uri.parse("http://pict.acm.org/pulzion-18/"));
        startActivity(intent);
    }

}
