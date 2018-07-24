package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pict.acm.pulzion18.model.EventEntry;
import com.pict.acm.pulzion18.model.EventSnapshot;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;

public class EventDetails extends AppCompatActivity {
    ImageView eventLogo;
    TextView eventTitle;
    TextView tagline;
    TextView description;
    TextView txt_rules;
    TextView txt_teams;
    TextView rules;
    TextView teams;
    TextView txt_fees;
    TextView fees;
    //FloatingActionButton registerBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference events = database.getReference().child(EVENTS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        ListInitializer initializer = ListInitializer.getInstance();
        EventSnapshot item = intent.getExtras().getParcelable("item");
        initDetails(initializer, item);

    }

    private void initDetails(ListInitializer initializer, EventSnapshot item) {
        eventLogo = findViewById(R.id.event_logo);
        eventTitle = findViewById(R.id.event_title);
        tagline = findViewById(R.id.tagline);
        description = findViewById(R.id.description);
        rules = findViewById(R.id.rules);
        teams = findViewById(R.id.teams);
        txt_rules = findViewById(R.id.txt_rules);
        txt_teams = findViewById(R.id.txt_teams);
        txt_fees = findViewById(R.id.txt_fees);
        fees = findViewById(R.id.fees);
        //registerBtn = findViewById(R.id.register);

        EventEntry entry = initializer.eventsMap.get(item.getName());
        eventLogo.setImageDrawable(getResources().getDrawable(entry.eventLogo, getTheme()));
        eventTitle.setText(entry.eventName);
        int color = getResources().getColor(entry.color);
        eventTitle.setTextColor(color);
        tagline.setTextColor(color);
        txt_teams.setTextColor(color);
        txt_rules.setTextColor(color);
        txt_fees.setTextColor(color);
        rules.setSingleLine(false);
        teams.setSingleLine(false);


        rules.setText("");
        teams.setText("");

        tagline.setText(item.getTagline());
        description.setText(item.getDescription());
        String[] ev_rules = item.getRules().split("\\.");
        for (int i = 0; i < ev_rules.length; i++) {
            rules.append(ev_rules[i] + " \n");
        }
        String[] ev_teams = item.getTeams().split("\\.");
        for (int i = 0; i < ev_teams.length; i++) {
            teams.append(ev_teams[i] + " \n");
        }
        fees.setText(item.getFees());
    }
}
