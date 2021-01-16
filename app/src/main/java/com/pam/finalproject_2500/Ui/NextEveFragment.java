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
import com.pam.finalproject_2500.Api.ApiServiceNext;
import com.pam.finalproject_2500.DetailActivity.DetailNextActivity;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.Adapter.EventAdapter;
import com.pam.finalproject_2500.Model.EventResponse;
import com.pam.finalproject_2500.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextEveFragment extends Fragment {

    private ArrayList<Event> listEvent;
    private RecyclerView rvEvent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nexteve, container, false);
        rvEvent = view.findViewById(R.id.rv_eventNext);
        rvEvent.setHasFixedSize(true);
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiServiceNext apiServiceNext = ApiClient.getRetrofitInstance().create(ApiServiceNext.class);
        Call<EventResponse> call = apiServiceNext.getNextEvent("1");

        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                listEvent = response.body().getEvents();

                EventAdapter eventAdapter = new EventAdapter(listEvent);
                rvEvent.setAdapter(eventAdapter);

                eventAdapter.setOnItemClickCallback(data -> {
                    Intent moveIntent = new Intent(getActivity(), DetailNextActivity.class);
                    moveIntent.putExtra(DetailNextActivity.ITEM_EXTRA, data);
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