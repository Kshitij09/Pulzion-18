package com.pict.acm.pulzion18.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.BackdropClickListener;
import com.pict.acm.pulzion18.NavigationIconClickListener;
import com.pict.acm.pulzion18.R;

import static com.pict.acm.pulzion18.Constants.PULZION.ABOUT;
import static com.pict.acm.pulzion18.Constants.PULZION.CONFIG;

public class AboutUs extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference aboutRef = database.getReference().child(CONFIG).child(ABOUT);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        final TextView about = findViewById(R.id.info);
        aboutRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String info = dataSnapshot.getValue(String.class);
                about.setText(info);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setupNavigationbar();
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        NavigationIconClickListener navIconListener = new NavigationIconClickListener(
                AboutUs.this,
                findViewById(R.id.main_view),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu));
        bottomAppBar.setNavigationOnClickListener(navIconListener);
        BackdropClickListener listener = new BackdropClickListener(this, navIconListener);
    }
}
