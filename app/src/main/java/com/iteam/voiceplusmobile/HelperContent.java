package com.iteam.voiceplusmobile;

import com.iteam.voiceplusmobile.ui.adminpanel.ordermanagement.CustomOrderModel;
import com.iteam.voiceplusmobile.ui.feedbackreview.FeedbackSchema;
import com.iteam.voiceplusmobile.ui.pricing.CustomListAdapter;
import com.iteam.voiceplusmobile.ui.pricing.CustomListDataModel;

public class HelperContent {

    public static int user_id = 0;
    public static String user_real_name = "";
    public static String user_phone_number = "";
    public static String user_address = "";
    public static String user_password = "";
    public static String user_role = "";
    public static String image = "";
    public static String created_at = "";
    public static int last_fragment = 0;

    /**
     * Default goes to profile
     * Detail Page View = 1 --- content --- order
     * Direct from menu = 2 ---- order
     */

    public static int getLast_fragment() {
        return last_fragment;
    }


    public static void setLast_fragment(int last_fragment) {
        HelperContent.last_fragment = last_fragment;
    }


    public static boolean isHas_pricing_flag() {
        return has_pricing_flag;
    }

    public static void setHas_pricing_flag(boolean has_pricing_flag) {
        HelperContent.has_pricing_flag = has_pricing_flag;
    }

    public static boolean has_pricing_flag = false;
    public static CustomListDataModel customListDataModel;

    public static CustomOrderModel getCustomOrderModel() {
        return customOrderModel;
    }

    public static void setCustomOrderModel(CustomOrderModel customOrderModel) {
        HelperContent.customOrderModel = customOrderModel;
    }

    public static CustomOrderModel customOrderModel;

    public static com.iteam.voiceplusmobile.ui.feedbackreview.CustomListDataModel getFeedbackSchema() {
        return feedbackSchema;
    }

    public static void setFeedbackSchema(com.iteam.voiceplusmobile.ui.feedbackreview.CustomListDataModel feedbackSchema) {
        HelperContent.feedbackSchema = feedbackSchema;
    }

    public static com.iteam.voiceplusmobile.ui.feedbackreview.CustomListDataModel feedbackSchema;


    public static CustomListDataModel getCustomListDataModel() {
        return customListDataModel;
    }

    public static void setCustomListDataModel(CustomListDataModel customListDataModel) {
        HelperContent.customListDataModel = customListDataModel;
    }


    public static String getUser_password() {
        return user_password;
    }

    public static void setUser_password(String user_password) {
        HelperContent.user_password = user_password;
    }


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
