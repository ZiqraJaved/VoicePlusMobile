package com.iteam.voiceplusmobile.ui.login.user_login;

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
    Call<LoginUser> sendLoginInformation(@Body LoginUser posts);
}