package com.rigel.comperio;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

public class ComperioApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

    private static ComperioApplication get(Context context) {
        return (ComperioApplication) context.getApplicationContext();
    }
}
