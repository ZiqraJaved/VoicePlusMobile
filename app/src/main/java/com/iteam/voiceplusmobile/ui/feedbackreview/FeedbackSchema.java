package com.iteam.voiceplusmobile.ui.feedbackreview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackSchema {

    @SerializedName("user_feedback")
    @Expose
    private String userFeedback;
    @SerializedName("user_phone_number")
    @Expose
    private String userPhoneNumber;
    @SerializedName("user_real_name")
    @Expose
    private String userRealName;

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

}