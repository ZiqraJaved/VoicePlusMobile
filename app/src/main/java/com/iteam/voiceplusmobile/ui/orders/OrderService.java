package com.iteam.voiceplusmobile.ui.orders;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrderService {


    @GET("repair_orders")
    Call<List<OrderSchema>> orderList();
}