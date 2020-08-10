package com.iteam.voiceplusmobile.ui.feedbackreview;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FeedBackService {

    String API_ROUTE = "feedback/";

    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<FeedbackSchema> feedBackFunction(@Body FeedbackSchema feedbackSchema);


    @GET(API_ROUTE)
    Call<List<FeedbackSchema>> feedbackList();

}
