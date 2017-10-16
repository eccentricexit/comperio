package com.rigel.comperio.viewmodel;

import android.content.Context;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.sync.SyncAdapter;


public class MainViewModel implements ViewModel {

    public MainViewModel(Context context) {
        SyncAdapter.initializeSyncAdapter(context);
    }
}
