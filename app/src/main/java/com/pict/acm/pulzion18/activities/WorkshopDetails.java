package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.BackdropClickListener;
import com.pict.acm.pulzion18.NavigationIconClickListener;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.WorkshopSnapshot;

import static com.pict.acm.pulzion18.Constants.PULZION.ETHICAL;
import static com.pict.acm.pulzion18.Constants.PULZION.ETHICAL_REG;
import static com.pict.acm.pulzion18.Constants.PULZION.IOT;
import static com.pict.acm.pulzion18.Constants.PULZION.IOT_REG;
import static com.pict.acm.pulzion18.Constants.PULZION.WORKSHOPS;

public class WorkshopDetails extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference workshopRef = database.getReference().child(WORKSHOPS);
    ImageView workshopLogo;
    TextView description;
    TextView date;
    TextView tagline;
    TextView contact;
    TextView fees;
    TextView txtContact;
    TextView txtFees;
    TextView txtDate;
    TextView txtName;
    FloatingActionButton btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_details);

        workshopLogo = findViewById(R.id.event_logo);
        txtName = findViewById(R.id.event_title);
        btnReg = findViewById(R.id.register);
        description = findViewById(R.id.description);
        tagline = findViewById(R.id.tagline);
        contact = findViewById(R.id.contact);
        fees = findViewById(R.id.fees);
        date = findViewById(R.id.date);
        txtContact = findViewById(R.id.txt_contact);
        txtFees = findViewById(R.id.txt_fees);
        txtDate = findViewById(R.id.txt_date);

        final String name = getIntent().getStringExtra("name");

        initDetails(name);
        setupNavigationbar();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web = new Intent(Intent.ACTION_VIEW);
                if (name.equals(ETHICAL)) {
                    web.setData(Uri.parse(ETHICAL_REG));
                } else {
                    web.setData(Uri.parse(IOT_REG));
                }
                startActivity(web);
            }
        });

    }

    private void initDetails(final String name) {
        int color = 0;
        workshopRef.keepSynced(true);
        Query query = null;
        switch (name) {
            case ETHICAL:
                Glide.with(this).load(R.drawable.hack).into(workshopLogo);
                color = getResources().getColor(R.color.pulzion_green);
                query = workshopRef.child(name);
                break;
            case IOT:
                Glide.with(this).load(R.drawable.iotw).into(workshopLogo);
                color = getResources().getColor(R.color.colorAccent);
                query = workshopRef.child(name);
                break;
        }
        tagline.setTextColor(color);
        txtName.setTextColor(color);
        txtContact.setTextColor(color);
        txtFees.setTextColor(color);
        txtDate.setTextColor(color);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WorkshopSnapshot model = dataSnapshot.getValue(WorkshopSnapshot.class);
                txtName.setText(model.getName());
                description.setText(model.getDescription());
                fees.setText(model.getFees());
                date.setText(model.getDate());
                contact.setText(model.getContact());
                tagline.setText(model.getTagline());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        NavigationIconClickListener navIconListener = new NavigationIconClickListener(
                WorkshopDetails.this,
                findViewById(R.id.detail_view),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu));
        bottomAppBar.setNavigationOnClickListener(navIconListener);
        BackdropClickListener listener = new BackdropClickListener(this, navIconListener);
    }
}
