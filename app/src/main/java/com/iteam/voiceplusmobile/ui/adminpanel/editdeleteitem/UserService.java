package com.iteam.voiceplusmobile.ui.adminpanel.editdeleteitem;

import com.iteam.voiceplusmobile.ui.adminpanel.manageitem.PricingSchema;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface UserService {
    @DELETE("delete/{id}")
    Call<PricingSchema> deleteItem(@Path("id") int id);
}
