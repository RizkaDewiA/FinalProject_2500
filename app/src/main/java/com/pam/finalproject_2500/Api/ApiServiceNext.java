package com.pam.finalproject_2500.Api;

import com.pam.finalproject_2500.Model.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceNext {
    // get didapatkan dari alamat link terakhir dri api nya
    @GET("1/eventsnextleague.php?id=4328")
    Call<EventResponse> getNextEvent(@Query("api_key") String apiKey);
}
