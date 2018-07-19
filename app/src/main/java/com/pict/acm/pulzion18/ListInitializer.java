package com.pict.acm.pulzion18;

import android.util.Log;

import com.pict.acm.pulzion18.model.EventEntry;

import java.util.ArrayList;

public class ListInitializer {
    private static final String TAG = ListInitializer.class.getSimpleName();
    private static ListInitializer INSTANCE = null;
    public ArrayList<EventEntry> eventEntries;

    private ListInitializer() {
        eventEntries = new ArrayList<>();
        eventEntries.add(new EventEntry("Bug Off", R.drawable.ev_bugoff, R.color.dark_terra_cotta));
        eventEntries.add(new EventEntry("Just Coding", R.drawable.ev_justcoding, R.color.cyan));
        eventEntries.add(new EventEntry("Code Buddy", R.drawable.ev_codebuddy, R.color.pulzion_green));
        eventEntries.add(new EventEntry("Cash N Code", R.drawable.ev_cashncode, R.color.yellow_red));
        eventEntries.add(new EventEntry("Electro Quest", R.drawable.ev_electroquest, R.color.persian_green));
        eventEntries.add(new EventEntry("Dextrous", R.drawable.ev_dextrous, R.color.dark_terra_cotta));
        eventEntries.add(new EventEntry("Fandom Quiz", R.drawable.ev_fandomquiz, R.color.persian_green));
        eventEntries.add(new EventEntry("Insight", R.drawable.ev_insight, R.color.cyan));
        eventEntries.add(new EventEntry("Cerebro", R.drawable.ev_cerebro, R.color.yellow_red));
        eventEntries.add(new EventEntry("Quiz to Bid", R.drawable.ev_quiz_to_bid, R.color.pulzion_green));
    }

    public static ListInitializer getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        Log.d(TAG, "getInstance: ");
        INSTANCE = new ListInitializer();
        return INSTANCE;
    }
}
