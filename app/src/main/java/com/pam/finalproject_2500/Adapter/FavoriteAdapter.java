package com.pam.finalproject_2500.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pam.finalproject_2500.DatabaseHelper;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ListViewHolder> {
    private final ArrayList<Event> listEvent;
    private Event event;

    private Context context;

    public FavoriteAdapter(ArrayList<Event> listEvent, Context context) {
        this.listEvent = listEvent;
        this.context = context;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite, parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        event = listEvent.get(position);

        holder.txtTitleFavor.setText(event.getStrEvent());
        holder.txtDateFavor.setText(event.getDateEvent());
        holder.txtTimeFavor.setText(event.getStrTime());
        holder.txtStatusFavor.setText(event.getStrStatus());

        Glide.with(holder.itemView)
                .load(event.getStrThumb())
                .into(holder.imgThumbFavor);

        Log.d("Testing Adapter", "Testing Link"+event.getStrThumb());

        holder.btnHapusFavor.setOnClickListener(view -> {
            event = listEvent.get(position);

            DatabaseHelper db = new DatabaseHelper(context);
            db.deleteFavorite(String.valueOf(event.getIdEvent()));

            notifyItemRemoved(position);
            listEvent.remove(position);

            Toast.makeText(context, "Favorite " + event.getStrEvent() + " telah terhapus", Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleFavor, txtDateFavor, txtTimeFavor, txtStatusFavor;
        ImageView imgThumbFavor;
        Button btnHapusFavor;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleFavor = itemView.findViewById(R.id.txtTitleFavor);
            txtDateFavor = itemView.findViewById(R.id.txtDateFavor);
            txtTimeFavor = itemView.findViewById(R.id.txtTimeFavor);
            txtStatusFavor = itemView.findViewById(R.id.txtStatusFavor);
            imgThumbFavor = itemView.findViewById(R.id.imgThumbFavor);
            btnHapusFavor = itemView.findViewById(R.id.btnHapusFavor);
        }
    }
}
