package com.iteam.voiceplusmobile.ui.adminpanel.additemmanagement;

import com.iteam.voiceplusmobile.ui.login.register.RegisterUser;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddItemServices {
    String API_ROUTE = "add_new_item";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<AddItem> addNewItem(@Body AddItem addItem);

}
