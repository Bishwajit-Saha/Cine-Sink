package com.bishwajit.cinemalyticsbuzz;

import android.app.Application;
import android.content.Context;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance()
    {
        return sInstance;
    }

    public static Context getContext()
    {
        return sInstance.getApplicationContext();
    }
}
