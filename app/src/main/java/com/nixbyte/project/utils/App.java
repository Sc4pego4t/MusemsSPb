package com.nixbyte.project.utils;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;


/**
 * Created by nixbyte on 24.01.17.
 */

public class App extends Application {
    private static Context context;


    private static final String DEV_MODE = "dev_mode";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }


}