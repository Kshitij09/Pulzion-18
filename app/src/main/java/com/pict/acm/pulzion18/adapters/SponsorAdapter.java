package com.pict.acm.pulzion18.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pict.acm.pulzion18.R;
import com.pict.acm.pulzion18.model.SponsorSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorHolder> {
    List<SponsorSnapshot> sponsors;

    public SponsorAdapter(List<SponsorSnapshot> sponsors) {
        this.sponsors = sponsors;
    }

    @NonNull
    @Override
    public SponsorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsors_list_item, parent, false);
        return new SponsorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorHolder holder, int position) {
        SponsorSnapshot snapshot = sponsors.get(position);
        holder.name.setText(snapshot.getName());
        Uri uri = Uri.parse(snapshot.getImageUrl());
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.sponsorLogo);
        holder.sponsorLogo.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public void setList(List<SponsorSnapshot> sponsors) {
        this.sponsors = new ArrayList<>(sponsors);
        notifyDataSetChanged();
    }

    public class SponsorHolder extends RecyclerView.ViewHolder {
        ImageView sponsorLogo;
        TextView name;

        public SponsorHolder(@NonNull View itemView) {
            super(itemView);
            sponsorLogo = itemView.findViewById(R.id.sponsor_logo);
            name = itemView.findViewById(R.id.txt_name);
        }
    }
}
