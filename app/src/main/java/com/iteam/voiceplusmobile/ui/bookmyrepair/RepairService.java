package com.iteam.voiceplusmobile.ui.bookmyrepair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RepairService {

    @POST("/repair_orders")
    @FormUrlEncoded
    Call<OrderSchema> bookRepair(@Body OrderSchema orderSchema);
//
//    Call<OrderSchema> bookRepair(
//            @Field("user_phone_number") String user_phone_number,
//            @Field("mobile_brand") String mobile_brand,
//            @Field("mobile_model") String mobile_model,
//            @Field("mobile_fault") String mobile_fault,
//            @Field("image") String image
//    );
}