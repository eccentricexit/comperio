package com.rigel.comperio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LoggingManager {

    private final Context context;
    private final String logTag;

    public LoggingManager(Context context, String logTag){
        this.context = context;
        this.logTag = logTag;
    }

    public void log(String logMessage) {
        Log.d(logTag,logMessage);
    }

    public void toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
