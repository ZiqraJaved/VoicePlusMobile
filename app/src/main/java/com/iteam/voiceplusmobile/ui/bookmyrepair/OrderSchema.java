package com.iteam.voiceplusmobile.ui.bookmyrepair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSchema {

    @SerializedName("user_phone_number")
    @Expose
    private String userPhoneNumber;
    @SerializedName("mobile_brand")
    @Expose
    private String mobileBrand;
    @SerializedName("mobile_model")
    @Expose
    private String mobileModel;
    @SerializedName("mobile_fault")
    @Expose
    private String mobileFault;
    @SerializedName("image")
    @Expose
    private Object image;

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(String mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getMobileFault() {
        return mobileFault;
    }

    public void setMobileFault(String mobileFault) {
        this.mobileFault = mobileFault;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

}