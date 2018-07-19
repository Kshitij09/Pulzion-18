package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.pict.acm.pulzion18.model.EventEntry;

public class EventDetails extends AppCompatActivity {
    ImageView eventLogo;
    TextView eventTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        ListInitializer initializer = ListInitializer.getInstance();
        int position = (int) intent.getExtras().get("pos");
        initDetails(initializer, position);

    }

    private void initDetails(ListInitializer initializer, int position) {
        eventLogo = findViewById(R.id.event_logo);
        eventTitle = findViewById(R.id.event_title);
        EventEntry entry = initializer.eventEntries.get(position);

        eventLogo.setImageDrawable(getResources().getDrawable(entry.eventLogo, getTheme()));
        eventTitle.setText(entry.eventName);
        eventTitle.setTextColor(getResources().getColor(entry.color));
    }
}
