package com.pict.acm.pulzion18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.widget.ImageView;

import com.pict.acm.pulzion18.adapters.EventsAdapter;

public class MainActivity extends AppCompatActivity implements EventsAdapter.OnItemClickListener {
    RecyclerView eventsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventsRecycler = findViewById(R.id.event_list);
        eventsRecycler.setHasFixedSize(true);

        setupRecyclerView();

        ListInitializer initializer = ListInitializer.getInstance();
        EventsAdapter adapter = new EventsAdapter(this, initializer.eventEntries);
        adapter.setOnItemClickListener(this);
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
        getWindow().setExitTransition(new Explode());
    }

    @Override
    public void onItemClick(ImageView view, int position) {
        Intent detailsActivity = new Intent(MainActivity.this, EventDetails.class);
        detailsActivity.putExtra("pos", position);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, ViewCompat.getTransitionName(view));
        startActivity(detailsActivity, optionsCompat.toBundle());
    }
}
