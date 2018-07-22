package com.pict.acm.pulzion18;

import android.util.Log;

import com.pict.acm.pulzion18.model.EventEntry;

import java.util.HashMap;

public class ListInitializer {
    private static final String TAG = ListInitializer.class.getSimpleName();
    private static ListInitializer INSTANCE = null;
    public HashMap<String, EventEntry> eventsMap;

    private ListInitializer() {
        eventsMap = new HashMap<>();
        eventsMap.put("Bug Off", new EventEntry("Bug Off", R.drawable.ev_bugoff, R.color.dark_terra_cotta));
        eventsMap.put("Just Coding", new EventEntry("Just Coding", R.drawable.ev_justcoding, R.color.french_blue));
        eventsMap.put("Code Buddy", new EventEntry("Code Buddy", R.drawable.ev_codebuddy, R.color.pulzion_green));
        eventsMap.put("Electroquest", new EventEntry("Electro Quest", R.drawable.ev_electroquest, R.color.persian_green));
        eventsMap.put("Dextrous", new EventEntry("Dextrous", R.drawable.ev_dextrous, R.color.dark_terra_cotta));
        eventsMap.put("Fandom Quiz", new EventEntry("Fandom Quiz", R.drawable.ev_fandomquiz, R.color.persian_green));
        eventsMap.put("Insight", new EventEntry("Insight", R.drawable.ev_insight, R.color.cyan));
        eventsMap.put("Cerebro", new EventEntry("Cerebro", R.drawable.ev_cerebro, R.color.yellow_red));
        eventsMap.put("Quiz to Bid", new EventEntry("Quiz to Bid", R.drawable.ev_quiz_to_bid, R.color.pulzion_green));
        eventsMap.put("Photoshop Royale", new EventEntry("Photoshop Royale", R.drawable.ev_dextrous, R.color.french_blue));
        eventsMap.put("Web and Android development", new EventEntry("Web and Android development", R.drawable.ev_codebuddy, R.color.mindaro));
    }

    public static ListInitializer getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        Log.d(TAG, "getInstance: New Instance");
        INSTANCE = new ListInitializer();
        return INSTANCE;
    }
}
