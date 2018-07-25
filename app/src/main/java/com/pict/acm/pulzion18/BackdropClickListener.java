package com.pict.acm.pulzion18;

import android.app.Activity;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.view.View;

import com.pict.acm.pulzion18.activities.AboutUs;
import com.pict.acm.pulzion18.activities.EventActivity;
import com.pict.acm.pulzion18.activities.SponsorsActivity;

public class BackdropClickListener implements View.OnClickListener {
    Activity activity;
    MaterialButton btnSponsor;
    MaterialButton btnAbout;
    MaterialButton btnEvents;
    NavigationIconClickListener navIconListener;

    public BackdropClickListener(Activity activity, NavigationIconClickListener navIconListener) {
        this.activity = activity;
        this.navIconListener = navIconListener;
        btnAbout = activity.findViewById(R.id.btn_about);
        btnEvents = activity.findViewById(R.id.btn_events);
        btnSponsor = activity.findViewById(R.id.btn_sponsors);
        btnSponsor.setOnClickListener(this);
        btnEvents.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_about:
                intent = new Intent(activity, AboutUs.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                break;
            case R.id.btn_events:
                intent = new Intent(activity, EventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                break;
            case R.id.btn_sponsors:
                intent = new Intent(activity, SponsorsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
                break;
        }
        navIconListener.toggleBackdrop(navIconListener.lastView);
        if (!activity.getLocalClassName().equals("activities.EventActivity"))
            activity.finish();
    }
}
