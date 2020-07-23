package com.iteam.voiceplusmobile.ui.bookmyrepair;

public class OrderSchema {

//    "user_phone_number": [
//            "This field is required."
//            ],
//            "mobile_brand": [
//            "This field is required."
//            ],
//            "mobile_model": [
//            "This field is required."

    public String getMobile_fault() {
        return mobile_fault;
    }

    public void setMobile_fault(String mobile_fault) {
        this.mobile_fault = mobile_fault;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getMobile_brand() {
        return mobile_brand;
    }

    public void setMobile_brand(String mobile_brand) {
        this.mobile_brand = mobile_brand;
    }

    public String getMobile_model() {
        return mobile_model;
    }

    public void setMobile_model(String mobile_model) {
        this.mobile_model = mobile_model;
    }

    private String mobile_fault;
    private String user_phone_number;
    private String mobile_brand;
    private String mobile_model;
}
