package com.iteam.voiceplusmobile.ui.login.user_login;

import com.iteam.voiceplusmobile.HelperContent;

public class LoginUser {
    String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getUser_account_id() {
        return user_account_id;
    }

    public void setUser_account_id(int user_account_id) {
        this.user_account_id = user_account_id;
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

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    int user_account_id;
    String user_phone_number;

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    String user_password;
    String user_real_name;
    String user_address;
    String image;
    String user_role;
    String created_at;


    public String[] data() {
        String[] content = new String[8];
        content[0] = "" + user_account_id;
        content[1] =detail;
        content[2] = user_phone_number;
        content[3] = user_real_name;
        content[4] = user_address;
        content[5] = user_role;
        content[6] = image;
        content[7] = created_at;
        return content;
    }
}