package com.pam.finalproject_2500.Model;

import com.google.gson.annotations.SerializedName;
import com.pam.finalproject_2500.Model.Event;

import java.util.ArrayList;

public class EventResponse {
    @SerializedName("events")
    private ArrayList<Event> events;

    @SerializedName("total_events")
    private int total_events;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public int getTotal_events() {
        return total_events;
    }

    public void setTotal_events(int total_events) {
        this.total_events = total_events;
    }
}
