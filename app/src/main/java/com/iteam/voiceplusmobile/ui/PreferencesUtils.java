package com.portal.jobportal.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.toolbox.StringRequest;

public class PreferencesUtils {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static PreferencesUtils preferencesUtils;

    private PreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences("fbise_quizapp_db", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static PreferencesUtils getInstance(Context mContext) {
        if (preferencesUtils == null)
            preferencesUtils = new PreferencesUtils(mContext);
        return preferencesUtils;
    }

    public void commitRecord() {
        editor.commit();
        editor.apply();
    }

    public void setFname(String fname) {
        editor.putString("fname", fname);
        commitRecord();
    }

    public String getFname() {
        return sharedPreferences.getString("fname", "");
    }

    public void setLname(String lname) {
        editor.putString("lname", lname);
        commitRecord();
    }

    public String getLname() {
        return sharedPreferences.getString("lname", "");
    }

    public void setEmail(String email) {
        editor.putString("email", email);
        commitRecord();
    }

    public String getEmail() {
        return sharedPreferences.getString("email", "");
    }

    public void setIsEmployer(int isEmployer) {
        editor.putInt("is_employer", isEmployer);
        commitRecord();
    }

    public int isEmployer() {
        return sharedPreferences.getInt("is_employer", -1);
    }

    public void setMemberID(int id) {
        editor.putInt("memID", id);
        commitRecord();
    }

    public int getMemberID() {
        return sharedPreferences.getInt("memID", -1);
    }

    public void setToken(String token) {
        editor.putString("token", token);
        commitRecord();
    }

    public void setLastLatLng(String latlng) {
        editor.putString("lastLatLng", latlng);
        commitRecord();
    }

    public String getLastLatLng() {
        return sharedPreferences.getString("lastLatLng", "");
    }

    public void setLastLocation(String location) {
        editor.putString("lastLocation", location);
        commitRecord();
    }

    public String getLastLocation() {
        return sharedPreferences.getString("lastLocation", "");
    }


    public String getToken() {
        return sharedPreferences.getString("token", "");
    }

    public void clearAll() {
        setEmail("");
        setMemberID(-1);
        setIsEmployer(0);
        setFname("");
        setLname("");
        setLastLatLng("");
        setLastLocation("");
        setContactNo("");
        setToken("");
    }

    public void setContactNo(String phone) {
        editor.putString("contact", phone);
        commitRecord();
    }

    public String getContactNo(){
        return sharedPreferences.getString("contact", "");
    }

}