package com.iteam.voiceplusmobile.ui.bookmyrepair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iteam.voiceplusmobile.ui.login.user_login.LoginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RepairService {

    String API_ROUTE = "repair_orders/";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<OrderSchema> bookRepair(@Body OrderSchema orderSchema);

}