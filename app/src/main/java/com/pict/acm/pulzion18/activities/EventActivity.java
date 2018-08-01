package com.pict.acm.pulzion18.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pict.acm.pulzion18.BackdropClickListener;
import com.pict.acm.pulzion18.GridMarginDecoration;
import com.pict.acm.pulzion18.NavigationIconClickListener;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.adapters.EventsAdapter;
import com.pict.acm.pulzion18.model.EventSnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;
import static com.pict.acm.pulzion18.Constants.PULZION.INDEX;

public class EventActivity extends AppCompatActivity implements EventsAdapter.OnItemClickListener {
    public static final String TAG = EventActivity.class.getSimpleName();
    RecyclerView eventsRecycler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference();
    EventsAdapter adapter;
    AVLoadingIndicatorView indicator;
    Boolean filterTechnical, filterNonTechnical;
    FirebaseRecyclerOptions<EventSnapshot> options;
    MaterialButton btnSponsor;
    MaterialButton btnAbout;
    MaterialButton btnEvents;
    ArrayList<EventSnapshot> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationbar();

        eventsRecycler = findViewById(R.id.event_list);
        indicator = findViewById(R.id.indicator);
        eventsRecycler.setHasFixedSize(true);

        indicator.show();
        eventsRecycler.setVisibility(View.GONE);
        events = new ArrayList<>();
        adapter = new EventsAdapter(EventActivity.this);
        adapter.setOnItemClickListener(EventActivity.this);
        final Query eventsRef = rootRef.child(EVENTS).orderByChild(INDEX);
        eventsRef.keepSynced(true);
        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events = new ArrayList<>();
                for (DataSnapshot snap :
                        dataSnapshot.getChildren()) {
                    EventSnapshot item = snap.getValue(EventSnapshot.class);
                    Log.d(TAG, "onDataChange: " + item.getName());
                    events.add(item);
                }
                adapter.setEventList(events);
                eventsRecycler.setAdapter(adapter);
                eventsRecycler.setVisibility(View.VISIBLE);
                setupRecyclerView();
                indicator.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        NavigationIconClickListener navIconListener = new NavigationIconClickListener(
                EventActivity.this,
                findViewById(R.id.event_list),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu));
        bottomAppBar.setNavigationOnClickListener(navIconListener);
        /*btnSponsor = findViewById(R.id.btn_sponsors);
        btnSponsor.setOnClickListener(this);
        btnAbout = findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(this);
        btnEvents = findViewById(R.id.btn_events);
        btnEvents.setOnClickListener(this);*/
        BackdropClickListener listener = new BackdropClickListener(this, navIconListener);
    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) eventsRecycler.getLayoutManager();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position % 6) {
                    case 5:
                        return 3;
                    case 3:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        eventsRecycler.addItemDecoration(new GridMarginDecoration(
                getResources().getDimensionPixelSize(R.dimen.grid_item_spacing)));
        eventsRecycler.setHasFixedSize(true);
        getWindow().setExitTransition(new Explode());
    }

    @Override
    public void onItemClick(ImageView view, EventSnapshot item) {
        Intent detailsActivity = new Intent(EventActivity.this, EventDetails.class);
        detailsActivity.putExtra("item", item);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(EventActivity.this, view, ViewCompat.getTransitionName(view));
        startActivity(detailsActivity, optionsCompat.toBundle());
    }

    public void launchWorkshop(View view) {

    }
}
