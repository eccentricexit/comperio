package com.rigel.comperio.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ComperioAuthService extends Service {

    private ComperioAuthenticator mAuthenticator;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAuthenticator = new ComperioAuthenticator(this);
    }
}