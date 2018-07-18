package com.pict.acm.pulzion18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pict.acm.pulzion18.adapters.EventsAdapter;
import com.pict.acm.pulzion18.model.EventEntry;

public class MainActivity extends AppCompatActivity {
    RecyclerView eventsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventsRecycler = findViewById(R.id.event_list);
        eventsRecycler.setHasFixedSize(true);

        setupRecyclerView();

        EventsAdapter adapter = new EventsAdapter(this, EventEntry.initEventsList());
        eventsRecycler.setAdapter(adapter);
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
    }
}
