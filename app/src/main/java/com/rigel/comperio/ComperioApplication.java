package com.rigel.comperio;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;
import com.rigel.comperio.sync.ComperioFactory;
import com.rigel.comperio.sync.ComperioService;
import com.rigel.comperio.sync.SyncAdapter;

import timber.log.Timber;

public class ComperioApplication extends Application {

    ComperioService comperioService;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        SyncAdapter.initializeSyncAdapter(this);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
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
