package com.iteam.voiceplusmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.iteam.voiceplusmobile.ui.PreferencesUtils;
import com.iteam.voiceplusmobile.ui.adminpanel.admin.AdminPanelActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!PreferencesUtils.getInstance(SplashActivity.this).getUname().equalsIgnoreCase("")) {
                    if (PreferencesUtils.getInstance(SplashActivity.this).getUType().equalsIgnoreCase("admin")) {
                        startActivity(new Intent(SplashActivity.this, AdminPanelActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        return;
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra("user_id", 0); //place user id here
                        startActivity(intent);
                        finish();
                        return;
                    }
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}