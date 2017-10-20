package com.rigel.comperio;

import android.app.Application;
import android.content.Context;

public class ComperioApplication extends Application {
    private static ComperioApplication get(Context context) {
        return (ComperioApplication) context.getApplicationContext();
    }
}
