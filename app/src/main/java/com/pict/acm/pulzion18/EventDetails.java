package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class EventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        int position = (int) intent.getExtras().get("pos");
        ImageView eventLogo = findViewById(R.id.event_logo);
        eventLogo.setImageDrawable(getResources().getDrawable(R.drawable.pasc, getTheme()));


        ListInitializer initializer = ListInitializer.getInstance();
        Toolbar toolbar = findViewById(R.id.AppBar);
        setSupportActionBar(toolbar);
        setTitle(initializer.eventEntries.get(position).eventName);
    }
}
