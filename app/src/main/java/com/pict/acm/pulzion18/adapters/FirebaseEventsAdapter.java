package com.pict.acm.pulzion18.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.EventSnapshot;

public class FirebaseEventsAdapter extends FirebaseRecyclerAdapter<EventSnapshot, FirebaseEventsAdapter.MainEventHolder> {
    private Context context;
    private OnItemClickListener listener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FirebaseEventsAdapter(Context context, @NonNull FirebaseRecyclerOptions<EventSnapshot> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MainEventHolder holder, int position, @NonNull EventSnapshot model) {
        holder.name.setText(model.getName());
        holder.eventLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.pasc, context.getTheme()));
        holder.bind(model.getName(), listener);
    }

    @NonNull
    @Override
    public MainEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);

        return new MainEventHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView view, String name);
    }

    class MainEventHolder extends RecyclerView.ViewHolder {
        ImageView eventLogo;
        TextView name;

        public MainEventHolder(@NonNull View itemView) {
            super(itemView);
            eventLogo = itemView.findViewById(R.id.event_logo);
            name = itemView.findViewById(R.id.event_title);
        }

        public void bind(final String name, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(eventLogo, name);
                }
            });
        }
    }
}
