package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.model.EventEntry;
import com.pict.acm.pulzion18.model.EventSnapshot;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;
import static com.pict.acm.pulzion18.Constants.PULZION.NAME;

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
        String name = (String) intent.getExtras().get("name");
        initDetails(initializer, name);

    }

    private void initDetails(ListInitializer initializer, String name) {
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

        EventEntry entry = initializer.eventsMap.get(name);
        eventLogo.setImageDrawable(getResources().getDrawable(entry.eventLogo, getTheme()));
        eventTitle.setText(entry.eventName);
        eventTitle.setTextColor(getResources().getColor(entry.color));
        tagline.setTextColor(getResources().getColor(entry.color));
        txt_teams.setTextColor(getResources().getColor(entry.color));
        txt_rules.setTextColor(getResources().getColor(entry.color));
        txt_fees.setTextColor(getResources().getColor(entry.color));
        rules.setSingleLine(false);
        teams.setSingleLine(false);
        Query record = events.orderByChild(NAME).equalTo(name);
        record.keepSynced(true);
        record.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    EventSnapshot eventSnapshot = snap.getValue(EventSnapshot.class);
                    rules.setText("");
                    teams.setText("");
                    Log.d("EventSnapshot", "onDataChange: " + dataSnapshot);
                    tagline.setText(eventSnapshot.getTagline());
                    description.setText(eventSnapshot.getDescription());
                    String[] ev_rules = eventSnapshot.getRules().split("\\.");
                    for (int i = 0; i < ev_rules.length; i++) {
                        rules.append(ev_rules[i] + " \n");
                    }
                    String[] ev_teams = eventSnapshot.getTeams().split("\\.");
                    for (int i = 0; i < ev_teams.length; i++) {
                        teams.append(ev_teams[i] + " \n");
                    }
                    fees.setText(eventSnapshot.getFees());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
