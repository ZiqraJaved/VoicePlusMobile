package com.iteam.voiceplusmobile.ui.login.register;

public class RegisterUser {
//  "user_address": "string",
//          "user_password": "string",
//          "user_phone_number": "string",
//          "user_real_name": "string"

    String user_address;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    String detail;

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    String user_password;
    String user_phone_number;
    String user_real_name;

    String user_info() {
        return detail;
    }
}
