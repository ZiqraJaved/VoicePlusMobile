package com.iteam.voiceplusmobile.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.iteam.voiceplusmobile.R;

public class PreferencesUtils {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static PreferencesUtils preferencesUtils;

    private PreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
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


    public String getUname() {
        return sharedPreferences.getString("uname", "");
    }

    public void setUname(String uname) {
        editor.putString("uname", uname);
        commitRecord();
    }

    public void setUType(String uname) {
        editor.putString("utype", uname);
        commitRecord();
    }

    public String getUType() {
        return sharedPreferences.getString("utype", "");
    }

    public void clearAll() {
        setUname("");
        setUType("");
    }

}