package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pict.acm.pulzion18.adapters.FirebaseEventsAdapter;
import com.pict.acm.pulzion18.model.EventSnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;

public class EventActivity extends AppCompatActivity implements FirebaseEventsAdapter.OnItemClickListener {
    RecyclerView eventsRecycler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference();
    FirebaseEventsAdapter adapter;
    AVLoadingIndicatorView indicator;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationbar();

        eventsRecycler = findViewById(R.id.event_list);
        indicator = findViewById(R.id.indicator);
        eventsRecycler.setHasFixedSize(true);

        setupRecyclerView();

        DatabaseReference query = rootRef.child(EVENTS);
        query.keepSynced(true);
        FirebaseRecyclerOptions<EventSnapshot> options = new FirebaseRecyclerOptions.Builder<EventSnapshot>()
                .setQuery(query, EventSnapshot.class)
                .build();
        adapter = new FirebaseEventsAdapter(EventActivity.this, options, indicator, eventsRecycler);
        adapter.setOnItemClickListener(this);
        eventsRecycler.setAdapter(adapter);
    }

    private void setupNavigationbar() {
        Toolbar bar = findViewById(R.id.app_bar);
        setSupportActionBar(bar);
        bar.setNavigationOnClickListener(new NavigationIconClickListener(
                EventActivity.this,
                findViewById(R.id.event_list),
                findViewById(R.id.backdrop),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.ic_menu),
                getResources().getDrawable(R.drawable.close_menu)));
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
    public void onItemClick(ImageView view, String name) {
        Intent detailsActivity = new Intent(EventActivity.this, EventDetails.class);
        detailsActivity.putExtra("name", name);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(EventActivity.this, view, ViewCompat.getTransitionName(view));
        startActivity(detailsActivity, optionsCompat.toBundle());
    }

    //test comment
}
