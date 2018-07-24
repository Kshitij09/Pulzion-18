package com.pict.acm.pulzion18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pict.acm.pulzion18.ListInitializer;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.EventEntry;
import com.pict.acm.pulzion18.model.EventSnapshot;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private ArrayList<EventSnapshot> eventList;
    private Context context;
    private OnItemClickListener listener;
    private ListInitializer initializer;

    public EventsAdapter(Context context) {
        eventList = new ArrayList<>();
        this.context = context;
        initializer = ListInitializer.getInstance();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        EventSnapshot entry = eventList.get(position);
        EventEntry resEntry = initializer.eventsMap.get(entry.getName());
        holder.eventTitle.setText(entry.getName());
        if (resEntry != null) {
            Glide.with(holder.itemView.getContext()).load(context.getResources().getDrawable(resEntry.eventLogo)).into(holder.eventLogo);
            holder.eventTitle.setTextColor(context.getResources().getColor(resEntry.color));
        }
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setEventList(ArrayList<EventSnapshot> list) {
        eventList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView view, EventSnapshot item);
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        ImageView eventLogo;
        //RelativeLayout layout;
        View itemView;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            eventLogo = itemView.findViewById(R.id.event_logo);
            eventTitle = itemView.findViewById(R.id.event_title);
            //layout = itemView.findViewById(R.id.layout);
        }

        public void bind(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(eventLogo, eventList.get(position));
                }
            });
        }
    }
}
