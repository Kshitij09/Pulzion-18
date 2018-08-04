package com.pict.acm.pulzion18;

import android.app.Activity;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.util.Log;
import android.view.View;

import com.pict.acm.pulzion18.activities.AboutUs;
import com.pict.acm.pulzion18.activities.EventActivity;
import com.pict.acm.pulzion18.activities.SponsorsActivity;
import com.pict.acm.pulzion18.activities.WorkshopActivity;

public class BackdropClickListener implements View.OnClickListener {
    Activity activity;
    MaterialButton btnSponsor;
    MaterialButton btnAbout;
    MaterialButton btnEvents;
    MaterialButton btnWorkshops;
    NavigationIconClickListener navIconListener;

    public BackdropClickListener(Activity activity, NavigationIconClickListener navIconListener) {
        this.activity = activity;
        this.navIconListener = navIconListener;
        btnAbout = activity.findViewById(R.id.btn_about);
        btnEvents = activity.findViewById(R.id.btn_events);
        btnSponsor = activity.findViewById(R.id.btn_sponsors);
        btnWorkshops = activity.findViewById(R.id.btn_workshops);
        btnSponsor.setOnClickListener(this);
        btnEvents.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnWorkshops.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_about:
                intent = new Intent(activity, AboutUs.class);
                break;
            case R.id.btn_events:
                intent = new Intent(activity, EventActivity.class);
                break;
            case R.id.btn_sponsors:
                intent = new Intent(activity, SponsorsActivity.class);
                break;
            case R.id.btn_workshops:
                intent = new Intent(activity, WorkshopActivity.class);
                break;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        navIconListener.toggleBackdrop(navIconListener.lastView);

        if (!activity.getLocalClassName().equals("activities.EventActivity") || !activity.getLocalClassName().equals("activities.HomeActivity")) {
            Log.d("LISTENER", "onClick: " + activity.getLocalClassName());
            activity.finish();
        }
    }
}
