package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.pict.acm.pulzion18.BackdropClickListener;
import com.pict.acm.pulzion18.NavigationIconClickListener;
import com.pict.acm.pulzion18.R;

import static com.pict.acm.pulzion18.Constants.PULZION.ETHICAL;
import static com.pict.acm.pulzion18.Constants.PULZION.IOT;

public class WorkshopActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgEthical;
    ImageView imgIOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);
        imgEthical = findViewById(R.id.img_ethical);
        imgEthical.setOnClickListener(this);
        imgIOT = findViewById(R.id.img_iot);
        imgIOT.setOnClickListener(this);
        setupNavigationbar();
    }

    @Override
    public void onClick(View view) {
        Intent details = new Intent(this, WorkshopDetails.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, ViewCompat.getTransitionName(view));
        if (view.getId() == R.id.img_ethical)
            details.putExtra("name", ETHICAL);
        else
            details.putExtra("name", IOT);
        startActivity(details, optionsCompat.toBundle());
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        NavigationIconClickListener navIconListener = new NavigationIconClickListener(
                WorkshopActivity.this,
                findViewById(R.id.main_view),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu));
        bottomAppBar.setNavigationOnClickListener(navIconListener);
        BackdropClickListener listener = new BackdropClickListener(this, navIconListener);
    }
}
