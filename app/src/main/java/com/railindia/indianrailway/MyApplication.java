package com.railindia.indianrailway;

import android.app.Application;

import com.startapp.sdk.adsbase.StartAppSDK;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StartAppSDK.init(this, getString(R.string.st_app_id), true); // Replace with your App ID
        StartAppSDK.setTestAdsEnabled(true);
    }
}
