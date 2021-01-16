package com.pam.finalproject_2500.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pam.finalproject_2500.Api.ApiClient;
import com.pam.finalproject_2500.Api.ApiServiceLast;
import com.pam.finalproject_2500.DetailActivity.DetailLastActivity;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.Model.EventResponse;
import com.pam.finalproject_2500.Adapter.EventScoreAdapter;
import com.pam.finalproject_2500.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LastEveFragment extends Fragment {

    private ArrayList<Event> listEvent;
    private RecyclerView rvEvent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lasteve, container, false);
        rvEvent = view.findViewById(R.id.rv_eventlast);
        rvEvent.setHasFixedSize(true);
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiServiceLast serviceLast = ApiClient.getRetrofitInstance().create(ApiServiceLast.class);
        Call<EventResponse> call = serviceLast.getLastEvent("1");

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                listEvent = response.body().getEvents();

                EventScoreAdapter eventScoreAdapter = new EventScoreAdapter(listEvent);
                rvEvent.setAdapter(eventScoreAdapter);

                eventScoreAdapter.setOnItemClickCallback(data -> {
                    Intent moveIntent = new Intent(getActivity(), DetailLastActivity.class);
                    moveIntent.putExtra(DetailLastActivity.ITEM_EXTRA, data);
                    startActivity(moveIntent);
                });
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {

            }
        });
        return view;
    }
}