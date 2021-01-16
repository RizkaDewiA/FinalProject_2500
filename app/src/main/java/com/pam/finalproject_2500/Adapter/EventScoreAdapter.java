package com.pam.finalproject_2500.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.R;

import java.util.ArrayList;

public class EventScoreAdapter extends RecyclerView.Adapter<EventScoreAdapter.ListViewHolder> {

    private ArrayList<Event> listEventScore;
    private Event event;
    private EventScoreAdapter.OnItemClickCallback onItemClickCallback;

    public EventScoreAdapter(ArrayList<Event> listEventScore) {
        this.listEventScore = listEventScore;
    }

    public void setOnItemClickCallback(EventScoreAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public EventScoreAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewlast = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hasil,parent,false);

        return new EventScoreAdapter.ListViewHolder(viewlast);
    }

    @Override
    public void onBindViewHolder(@NonNull EventScoreAdapter.ListViewHolder holder, int position) {
        final Event eventlast = listEventScore.get(position);

        holder.txtTitleHome.setText(eventlast.getStrHomeTeam());
        holder.txtTitleAway.setText(eventlast.getStrAwayTeam());
        holder.txtDateScore.setText(eventlast.getDateEvent());
        holder.txtScoreHome.setText(eventlast.getIntHomeScore());
        holder.txtScoreAway.setText(eventlast.getIntAwayScore());

        Glide.with(holder.itemView)
                .load(eventlast.getStrThumb())
                .into(holder.imgThumbScore);

        holder.btnDetailScore.setOnClickListener(view -> {
            onItemClickCallback.onItemClicked(eventlast);
        });
    }

    @Override
    public int getItemCount() {
        return listEventScore.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHome, txtTitleAway, txtDateScore, txtScoreHome, txtScoreAway;
        ImageView imgThumbScore;
        Button btnDetailScore;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleHome = itemView.findViewById(R.id.txtHomeTitle);
            txtTitleAway = itemView.findViewById(R.id.txtAwayTitle);
            txtScoreHome = itemView.findViewById(R.id.txtHomeScore);
            txtScoreAway = itemView.findViewById(R.id.txtAwayScore);
            txtDateScore = itemView.findViewById(R.id.txtDateScore);
            imgThumbScore = itemView.findViewById(R.id.imgThumbScore);
            btnDetailScore = itemView.findViewById(R.id.btnDetailScore);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Event data);
    }
}
