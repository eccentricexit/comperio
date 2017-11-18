package com.rigel.comperio;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.rigel.comperio.sync.ComperioFactory;
import com.rigel.comperio.sync.ComperioService;
import com.rigel.comperio.sync.SyncAdapter;

import timber.log.Timber;

public class ComperioApplication extends Application {

    private ComperioService comperioService;
    private InterstitialAd interstitialAd;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        initAds();
    }

    private void initAds() {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public ComperioService getComperioService() {
        if (comperioService == null) {
            comperioService = ComperioFactory.create();
        }

        return comperioService;
    }

    public InterstitialAd getInterstitialAd(){
        return interstitialAd;
    }

    public static ComperioApplication get(Context context) {
        return (ComperioApplication) context.getApplicationContext();
    }


}
