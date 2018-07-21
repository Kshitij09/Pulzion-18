package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.button.MaterialButton;
import android.support.design.chip.Chip;
import android.support.design.widget.FloatingActionButton;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pict.acm.pulzion18.adapters.FirebaseEventsAdapter;
import com.pict.acm.pulzion18.model.EventSnapshot;
import com.wang.avi.AVLoadingIndicatorView;

import static com.pict.acm.pulzion18.Constants.PULZION.EVENTS;
import static com.pict.acm.pulzion18.Constants.PULZION.INDEX;
import static com.pict.acm.pulzion18.Constants.PULZION.NONTECH;
import static com.pict.acm.pulzion18.Constants.PULZION.TECHNICAL;
import static com.pict.acm.pulzion18.Constants.PULZION.TYPE;

public class EventActivity extends AppCompatActivity implements FirebaseEventsAdapter.OnItemClickListener {
    public static final String TAG = EventActivity.class.getSimpleName();
    RecyclerView eventsRecycler;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference();
    FirebaseEventsAdapter adapter;
    AVLoadingIndicatorView indicator;
    FloatingActionButton btnFilter;
    Boolean filterTechnical, filterNonTechnical;
    Query query;
    FirebaseRecyclerOptions<EventSnapshot> options;
    MaterialButton btnSponsor;

    @Override
    protected void onStart() {
        super.onStart();
        if (filterNonTechnical || filterNonTechnical)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (filterTechnical || filterNonTechnical)
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
        btnFilter = findViewById(R.id.filter);
        btnSponsor = findViewById(R.id.btn_sponsors);
        btnSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventActivity.this, SponsorsActivity.class));
            }
        });

        Intent intent = getIntent();
        filterTechnical = intent.getBooleanExtra(TECHNICAL, true);
        filterNonTechnical = intent.getBooleanExtra(NONTECH, true);


        if (!filterTechnical && !filterNonTechnical) {
            indicator.hide();
            eventsRecycler.setVisibility(View.INVISIBLE);
        } else {
            if (filterTechnical && filterNonTechnical)
                query = rootRef.child(EVENTS).orderByChild(INDEX);
            else if (filterTechnical)
                query = rootRef.child(EVENTS).orderByChild(TYPE).equalTo(TECHNICAL);
            else if (filterNonTechnical)
                query = rootRef.child(EVENTS).orderByChild(TYPE).equalTo(NONTECH);
            query.keepSynced(true);
            FirebaseRecyclerOptions<EventSnapshot> options = new FirebaseRecyclerOptions.Builder<EventSnapshot>()
                    .setQuery(query, EventSnapshot.class)
                    .build();
            adapter = new FirebaseEventsAdapter(EventActivity.this, options, indicator, eventsRecycler);
            adapter.setOnItemClickListener(this);
            eventsRecycler.setAdapter(adapter);
            setupRecyclerView();
        }
    }

    private void setupNavigationbar() {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        bottomAppBar.setNavigationOnClickListener(new NavigationIconClickListener(
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

    public void LaunchFilterBottomSheet(View view) {
        FilterBottomSheet bottomSheet = new FilterBottomSheet();
        bottomSheet.setTech(filterTechnical);
        bottomSheet.setNonTech(filterNonTechnical);
        bottomSheet.show(getSupportFragmentManager(), "filterEvents");
    }

    public void FilterList(View view) {
        Chip chip = ((Chip) view);
        switch (view.getId()) {
            case R.id.ch_technical:
                markChip(chip, filterTechnical);
                filterTechnical = !filterTechnical;
                break;
            case R.id.ch_non_technical:
                markChip(chip, filterNonTechnical);
                filterNonTechnical = !filterNonTechnical;
                break;
        }
        Log.d(TAG, "FilterList: Tech" + filterTechnical);
        Log.d(TAG, "FilterList: Non-Tech" + filterNonTechnical);
        Intent reLaunch = new Intent(EventActivity.this, EventActivity.class);
        reLaunch.putExtra(TECHNICAL, filterTechnical);
        reLaunch.putExtra(NONTECH, filterNonTechnical);
        startActivity(reLaunch);
    }

    private void markChip(Chip chip, Boolean state) {
        if (state) {
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            chip.setChipStrokeColorResource(R.color.colorPrimary);
            chip.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            chip.setChipBackgroundColorResource(R.color.colorPrimary);
            chip.setChipStrokeColorResource(android.R.color.transparent);
            chip.setTextColor(getResources().getColor(android.R.color.white));
        }
    }
}
