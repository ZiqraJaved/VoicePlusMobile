package com.iteam.voiceplusmobile.ui.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    String API_ROUTE = "login_user";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<Login> sendLoginInformation(@Body Login posts);
}