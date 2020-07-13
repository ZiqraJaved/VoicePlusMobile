package com.iteam.voiceplusmobile.ui.login;

import com.iteam.voiceplusmobile.HelperContent;

public class Login {
    private String user_password;

    private String user_name;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;
    private int user_account_id;
    private String user_account_name;

    public int getUser_account_id() {
        return user_account_id;
    }

    public void setUser_account_id(int user_account_id) {
        this.user_account_id = user_account_id;
    }

    public String getUser_account_name() {
        return user_account_name;
    }

    public void setUser_account_name(String user_account_name) {
        this.user_account_name = user_account_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {

        HelperContent.user_id = user_account_id;
        HelperContent.user_name = user_name;
        HelperContent.user_real_name = user_account_name;

        return "Login{" +
                "detail="+detail +
                "user_name=" + user_name +
                ", user_account_id=" + user_account_id +
                ", user_account_name='" + user_account_name + '\'' +
                '}';
    }


}