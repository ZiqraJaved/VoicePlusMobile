package com.iteam.voiceplusmobile.ui.login.register;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RegisterUserService {

    String API_ROUTE = "register_user/";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<RegisterUser> sendLoginInformation(@Body RegisterUser registerUser);
}