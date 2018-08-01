package com.pict.acm.pulzion18.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.adapters.SponsorAdapter;
import com.pict.acm.pulzion18.model.SponsorSnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import static com.pict.acm.pulzion18.Constants.PULZION.CONFIG;
import static com.pict.acm.pulzion18.Constants.PULZION.PREV_SPONSORS;
import static com.pict.acm.pulzion18.Constants.PULZION.SPONSORS;
import static com.pict.acm.pulzion18.Constants.PULZION.VISIBLE;

public class SponsorsActivity extends AppCompatActivity {
    RecyclerView recyclerSponsors;
    SponsorAdapter adapter;
    public static final String TAG = SponsorsActivity.class.getSimpleName();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference sponsorRef = database.getReference().child(SPONSORS);
    DatabaseReference configRef = database.getReference().child(CONFIG).child(PREV_SPONSORS);
    TextView prevTitle;
    AVLoadingIndicatorView indicator;
    List<SponsorSnapshot> sponsors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerSponsors = findViewById(R.id.recycler_sponsors);
        indicator = findViewById(R.id.indicator);
        prevTitle = findViewById(R.id.txt_previous_sponsors);
        sponsors = new ArrayList<>();

        sponsorRef.keepSynced(true);
        recyclerSponsors.setHasFixedSize(true);
        adapter = new SponsorAdapter(sponsors);
        recyclerSponsors.setAdapter(adapter);
        sponsorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sponsors = new ArrayList<>();
                for (DataSnapshot snap :
                        dataSnapshot.getChildren()) {
                    SponsorSnapshot model = snap.getValue(SponsorSnapshot.class);
                    sponsors.add(model);
                }
                adapter.setList(sponsors);
                indicator.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        configRef.keepSynced(true);
        configRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String visibility = dataSnapshot.getValue(String.class);
                if (visibility.equals(VISIBLE))
                    prevTitle.setVisibility(View.VISIBLE);
                else
                    prevTitle.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
