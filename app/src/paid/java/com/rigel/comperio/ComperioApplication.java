package com.rigel.comperio;

import android.app.Application;
import android.content.Context;

import com.rigel.comperio.sync.ComperioFactory;
import com.rigel.comperio.sync.ComperioService;

import timber.log.Timber;

public class ComperioApplication extends Application {

    private ComperioService comperioService;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    public ComperioService getComperioService() {
        if (comperioService == null) {
            comperioService = ComperioFactory.create();
        }

        return comperioService;
    }

    public static ComperioApplication get(Context context) {
        return (ComperioApplication) context.getApplicationContext();
    }


}
