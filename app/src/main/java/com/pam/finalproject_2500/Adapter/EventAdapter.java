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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ListViewHolder> {

    private ArrayList<Event> listEventUtama;
    private Event event;
    private OnItemClickCallback onItemClickCallback;

    public EventAdapter(ArrayList<Event> listEventUtama) {
        this.listEventUtama = listEventUtama;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewlast = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

        return new ListViewHolder(viewlast);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        final Event eventnext = listEventUtama.get(position);

        holder.txtTitleUtama.setText(eventnext.getStrEvent());
        holder.txtDateUtama.setText(eventnext.getDateEvent());
        holder.txtTimeUtama.setText(eventnext.getStrTime());

        Glide.with(holder.itemView)
                .load(eventnext.getStrThumb())
                .into(holder.imgThumbUtama);

        holder.btnDetailUtama.setOnClickListener(view -> {
            onItemClickCallback.onItemClicked(eventnext);
        });

    }

    @Override
    public int getItemCount() {
        return listEventUtama.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleUtama, txtDateUtama, txtTimeUtama;
        ImageView imgThumbUtama;
        Button btnDetailUtama;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleUtama = itemView.findViewById(R.id.txtTitleUtama);
            txtDateUtama = itemView.findViewById(R.id.txtDateUtama);
            txtTimeUtama = itemView.findViewById(R.id.txtTimeUtama);
            imgThumbUtama = itemView.findViewById(R.id.imgThumbUtama);
            btnDetailUtama = itemView.findViewById(R.id.btnDetailUtama);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Event data);
    }
}
