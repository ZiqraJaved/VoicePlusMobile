package com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrderService {

    @GET("repair_orders")
    Call<List<OrderSchema>> repairList();
}