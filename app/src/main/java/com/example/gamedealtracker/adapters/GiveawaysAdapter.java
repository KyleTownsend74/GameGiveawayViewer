package com.example.gamedealtracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gamedealtracker.DetailActivity;
import com.example.gamedealtracker.R;
import com.example.gamedealtracker.models.Giveaway;

import org.parceler.Parcels;

import java.util.List;

public class GiveawaysAdapter extends RecyclerView.Adapter<GiveawaysAdapter.ViewHolder> {

    Context context;
    List<Giveaway> giveaways;

    public GiveawaysAdapter(Context context, List<Giveaway> giveaways) {
        this.context = context;
        this.giveaways = giveaways;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giveaway, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giveaway giveaway = giveaways.get(position);
        holder.bind(giveaway);
    }

    @Override
    public int getItemCount() {
        return giveaways.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        ImageView ivGiveawayItem;
        TextView tvGiveawayItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivGiveawayItem = itemView.findViewById(R.id.ivGiveawayItem);
            tvGiveawayItem = itemView.findViewById(R.id.tvGiveawayItem);
        }

        public void bind(Giveaway giveaway) {
            Glide.with(context)
                    .load(giveaway.getThumbnailUrl())
                    .placeholder(new ColorDrawable(Color.LTGRAY))
                    .into(ivGiveawayItem);
            tvGiveawayItem.setText(giveaway.getTitle());

            // Set on click listener for entire item
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("giveaway", Parcels.wrap(giveaway));
                    context.startActivity(i);
                }
            });
        }
    }
}
