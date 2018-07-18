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
    private OnItemClickListener listener;

    public EventsAdapter(Context context, ArrayList<EventEntry> eventList) {
        this.eventList = eventList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        /*final EventViewHolder eventViewHolder = new EventViewHolder(view);
        eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onItemClick(eventViewHolder.eventLogo);
            }
        });*/
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventEntry entry = eventList.get(position);
        holder.eventTitle.setText(entry.eventName);
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView view, int position);
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        public ImageView eventLogo;
        View itemView;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            eventLogo = itemView.findViewById(R.id.event_logo);
            eventTitle = itemView.findViewById(R.id.event_title);
        }

        public void bind(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(eventLogo, position);
                }
            });
        }
    }
}
