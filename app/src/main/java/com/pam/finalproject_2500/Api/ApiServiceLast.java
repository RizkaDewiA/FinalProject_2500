package com.pam.finalproject_2500.Api;

import com.pam.finalproject_2500.Model.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceLast {
    // get didapatkan dari alamat link terakhir dri api nya
    @GET("1/eventspastleague.php?id=4328")
    Call<EventResponse> getLastEvent(@Query("api_key") String apiKey);
}
