package com.pam.finalproject_2500.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.finalproject_2500.DatabaseHelper;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.Adapter.FavoriteAdapter;
import com.pam.finalproject_2500.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvEveFavorite;
    private ArrayList<Event> listEvent;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvEveFavorite = view.findViewById(R.id.rv_favorite);
        rvEveFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseHelper db = new DatabaseHelper(getContext());
        listEvent = db.getAllFavorite();

        if(listEvent.size() != 0){
            FavoriteAdapter adapter = new FavoriteAdapter(listEvent, getContext());
            rvEveFavorite.setAdapter(adapter);
        }

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.optionmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        DatabaseHelper db = new DatabaseHelper(getContext());
        switch (id) {
            case R.id.opmSemuaEvent:
                listEvent = db.getAllFavorite();

                if(listEvent.size() != 0){
                    FavoriteAdapter adapter = new FavoriteAdapter(listEvent, getContext());
                    rvEveFavorite.setAdapter(adapter);
                }
                return true;

            case R.id.opmLastEvent:
                listEvent = db.getLastFavorite();

                if(listEvent.size() != 0){
                    FavoriteAdapter adapter = new FavoriteAdapter(listEvent, getContext());
                    rvEveFavorite.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Favorit Untuk Last Event Belum Ditambahkan", Toast.LENGTH_SHORT).show();}
                return true;

            case R.id.opmNextEvent:
                listEvent = db.getNextFavorite();

                if(listEvent.size() != 0){
                    FavoriteAdapter adapter = new FavoriteAdapter(listEvent, getContext());
                    rvEveFavorite.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Favorit Untuk Next Event Belum Ditambahkan", Toast.LENGTH_SHORT).show();}
                return true;
        }

        return false;
    }

}