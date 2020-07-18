package com.iteam.voiceplusmobile;

public class HelperContent {

    public static int user_id = 0;
    public static String user_real_name = "";
    public static String user_phone_number = "";
    public static String user_address = "";

    public static String getUser_password() {
        return user_password;
    }

    public static void setUser_password(String user_password) {
        HelperContent.user_password = user_password;
    }

    public static String user_password = "";
    public static String user_role = "";
    public static String image = "";
    public static String created_at = "";

    public static String getUser_phone_number() {
        return user_phone_number;
    }

    public static void setUser_phone_number(String user_phone_number) {
        HelperContent.user_phone_number = user_phone_number;
    }

    public static String getUser_address() {
        return user_address;
    }

    public static void setUser_address(String user_address) {
        HelperContent.user_address = user_address;
    }

    public static String getUser_role() {
        return user_role;
    }

    public static void setUser_role(String user_role) {
        HelperContent.user_role = user_role;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        HelperContent.image = image;
    }

    public static String getCreated_at() {
        return created_at;
    }

    public static void setCreated_at(String created_at) {
        HelperContent.created_at = created_at;
    }


    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        HelperContent.user_id = user_id;
    }

    public static String getUser_real_name() {
        return user_real_name;
    }

    public static void setUser_real_name(String user_real_name) {
        HelperContent.user_real_name = user_real_name;
    }


    public static void setAllNull() {
        setUser_id(0);
        setUser_real_name("");
        setUser_phone_number("");
        setUser_address("");
        setUser_role("");
        setImage("");
        setCreated_at("");

    }
}
