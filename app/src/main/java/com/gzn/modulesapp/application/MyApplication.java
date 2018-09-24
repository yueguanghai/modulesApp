package com.gzn.modulesapp.application;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication sApplication=null;
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static MyApplication getInstance() {
        return sApplication;
    }
}
