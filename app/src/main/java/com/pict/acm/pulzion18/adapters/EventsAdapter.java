package com.pict.acm.pulzion18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.EventEntry;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private ArrayList<EventEntry> eventList;
    private Context context;

    public EventsAdapter(Context context, ArrayList<EventEntry> eventList) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventEntry entry = eventList.get(position);
        holder.eventTitle.setText(entry.eventName);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        ImageView eventLogo;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventLogo = itemView.findViewById(R.id.event_logo);
            eventTitle = itemView.findViewById(R.id.event_title);
        }
    }
}
