package com.pict.acm.pulzion18.adapters;

import android.content.Context;
import android.content.Intent;
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
    Context context;

    public SponsorAdapter(Context context, List<SponsorSnapshot> sponsors) {
        this.context = context;
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
        holder.sponsorType.setText(snapshot.getSponserType());
        holder.bind(snapshot.getWebsite());
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
        ImageView sponsorSite;
        TextView name;
        TextView sponsorType;

        public SponsorHolder(@NonNull View itemView) {
            super(itemView);
            sponsorLogo = itemView.findViewById(R.id.sponsor_logo);
            sponsorSite = itemView.findViewById(R.id.link_website);
            name = itemView.findViewById(R.id.txt_name);
            sponsorType = itemView.findViewById(R.id.sponsor_type);
        }

        public void bind(final String website) {
            sponsorSite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent web = new Intent(Intent.ACTION_VIEW);
                    web.setData(Uri.parse(website));
                    context.startActivity(web);
                }
            });
        }
    }
}
