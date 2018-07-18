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
        eventEntries.add(new EventEntry("Bug Off"));
        eventEntries.add(new EventEntry("Just Coding"));
        eventEntries.add(new EventEntry("Code Buddy"));
        eventEntries.add(new EventEntry("Cash N Code"));
        eventEntries.add(new EventEntry("Electro Quest"));
        eventEntries.add(new EventEntry("Dextrous"));
        eventEntries.add(new EventEntry("Fandom Quiz"));
        eventEntries.add(new EventEntry("Insight"));
        eventEntries.add(new EventEntry("Cerebro"));
        eventEntries.add(new EventEntry("Quiz to Bid"));
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
