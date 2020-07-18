package com.iteam.voiceplusmobile.ui.login.profile_update;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateUserService {

    String API_ROUTE = "update_user";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<UpdateUser> sendUpdateProfileInformation(@Body UpdateUser updateUser);
}