package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pict.acm.pulzion18.BackdropClickListener;
import com.pict.acm.pulzion18.ListInitializer;
import com.pict.acm.pulzion18.NavigationIconClickListener;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.EventEntry;
import com.pict.acm.pulzion18.model.EventSnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;
import static com.pict.acm.pulzion18.Constants.PULZION.SITE_LINK;

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
    TextView txt_contact;
    TextView contact;

    FloatingActionButton registerBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference events = database.getReference().child(EVENTS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setupNavigationbar();
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
        txt_contact = findViewById(R.id.txt_contact);
        fees = findViewById(R.id.fees);
        contact = findViewById(R.id.contact);

        registerBtn = findViewById(R.id.register);

        EventEntry entry = initializer.eventsMap.get(item.getName());
        Glide.with(this).load(getResources().getDrawable(entry.eventLogo)).into(eventLogo);
        eventTitle.setText(entry.eventName);
        int color = getResources().getColor(entry.color);
        eventTitle.setTextColor(color);
        tagline.setTextColor(color);
        txt_teams.setTextColor(color);
        txt_rules.setTextColor(color);
        txt_fees.setTextColor(color);
        txt_contact.setTextColor(color);
        rules.setSingleLine(false);
        teams.setSingleLine(false);


        rules.setText("");
        teams.setText("");
        contact.setText("");

        tagline.setText(item.getTagline());
        description.setText(item.getDescription());
        String[] ev_rules = item.getRules().split("\\.");
        if (item.getName().equals("Photoshop Royale")) {
            rules.append(ev_rules[0] + "." + ev_rules[1] + "." + ev_rules[2] + " \n");
            for (int i = 3; i < ev_rules.length; i++) {
                rules.append(ev_rules[i] + " \n");
            }
        } else {
            for (int i = 0; i < ev_rules.length; i++) {
                rules.append(ev_rules[i] + " \n");
            }
        }
        String[] ev_teams = item.getTeams().split("\\.");
        for (int i = 0; i < ev_teams.length; i++) {
            teams.append(ev_teams[i] + " \n");
        }
        fees.setText(item.getFees());
        HashMap<String, Long> contacts = item.getContact();
        for (Map.Entry<String, Long> pair : contacts.entrySet()) {
            contact.append(pair.getKey() + ": " + pair.getValue() + " \n");
        }
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        NavigationIconClickListener navIconListener = new NavigationIconClickListener(
                EventDetails.this,
                findViewById(R.id.detail_view),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu));
        bottomAppBar.setNavigationOnClickListener(navIconListener);
        BackdropClickListener listener = new BackdropClickListener(this, navIconListener);
    }

    public void launchRegisterSite(View v) {
        Intent web = new Intent(Intent.ACTION_VIEW);
        web.setData(Uri.parse(SITE_LINK));
        startActivity(web);
    }

}
