package com.pict.acm.pulzion18;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pict.acm.pulzion18.activities.AboutUs;

public class BackdropClickListener implements View.OnClickListener {
    Context context;

    BackdropClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_about:
                context.startActivity(new Intent(context, AboutUs.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.btn_events:
                context.startActivity(new Intent(context, EventActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.btn_sponsors:
                context.startActivity(new Intent(context, SponsorsActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }
}
