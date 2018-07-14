package com.pict.acm.pulzion18.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class EventEntry {
    public String eventName;
    public Drawable eventLogo;

    public EventEntry(String eventName) {
        this.eventName = eventName;
    }

    public static ArrayList<EventEntry> initEventsList() {
        ArrayList<EventEntry> dummyList = new ArrayList<>();
        dummyList.add(new EventEntry("Bug Off"));
        dummyList.add(new EventEntry("Just Coding"));
        dummyList.add(new EventEntry("Code Buddy"));
        dummyList.add(new EventEntry("Cash N Code"));
        dummyList.add(new EventEntry("Electro Quest"));
        dummyList.add(new EventEntry("Dextrous"));
        dummyList.add(new EventEntry("Fandom Quiz"));
        dummyList.add(new EventEntry("Insight"));
        dummyList.add(new EventEntry("Cerebro"));
        dummyList.add(new EventEntry("Quiz to Bid"));
        return dummyList;
    }
}
