package com.iteam.voiceplusmobile.ui.adminpanel.manageitem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PricingService {

    @GET("pricing")
    Call<List<PricingSchema>> pricesList();
}