package com.rigel.comperio.ViewModel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;


public class MainViewModel implements ViewModel {

    @NonNull
    private final Navigator navigator;

    public MainViewModel(Navigator navigator){
        this.navigator = navigator;
    }
}
