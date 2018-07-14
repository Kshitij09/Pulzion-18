package com.pict.acm.pulzion18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.pict.acm.pulzion18.adapters.EventsAdapter;
import com.pict.acm.pulzion18.model.EventEntry;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView eventsRecycler = findViewById(R.id.event_list);
        eventsRecycler.setHasFixedSize(true);

        StaggeredGridLayoutManager grid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        grid.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        eventsRecycler.setLayoutManager(grid);

        EventsAdapter adapter = new EventsAdapter(this, EventEntry.initEventsList());
        eventsRecycler.setAdapter(adapter);
    }
}
