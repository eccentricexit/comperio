package com.rigel.comperio.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.sync.SyncAdapter;


public class MainViewModel implements ViewModel {

    public MainViewModel(Context context) {
        SyncAdapter.initializeSyncAdapter(context);
    }
}
