package com.iteam.voiceplusmobile.ui.adminpanel.addneworder;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddOrderServices {
    String API_ROUTE = "repair_orders/";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<AddNewOrder> addorderinfo(@Body AddNewOrder posts);
}
