package com.iteam.voiceplusmobile.ui.bookmyrepair;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RepairService {

    String API_ROUTE = "/repair_orders/";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<OrderSchema> bookMyOrder(@Body OrderSchema orderSchema);
}