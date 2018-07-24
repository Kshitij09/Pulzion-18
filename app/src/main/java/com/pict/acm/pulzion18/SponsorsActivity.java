package com.pict.acm.pulzion18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.adapters.SponsorAdapter;
import com.pict.acm.pulzion18.model.SponsorSnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import static com.pict.acm.pulzion18.Constants.PULZION.SPONSORS;

public class SponsorsActivity extends AppCompatActivity {
    RecyclerView recyclerSponsors;
    SponsorAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference sponsorRef = database.getReference().child(SPONSORS);
    AVLoadingIndicatorView indicator;
    List<SponsorSnapshot> sponsors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerSponsors = findViewById(R.id.recycler_sponsors);
        indicator = findViewById(R.id.indicator);
        sponsors = new ArrayList<>();

        sponsorRef.keepSynced(true);
        recyclerSponsors.setHasFixedSize(true);
        sponsorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sponsors = new ArrayList<>();
                for (DataSnapshot snap :
                        dataSnapshot.getChildren()) {
                    SponsorSnapshot model = snap.getValue(SponsorSnapshot.class);
                    sponsors.add(model);
                }
                adapter = new SponsorAdapter(sponsors);
                recyclerSponsors.setAdapter(adapter);
                indicator.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
