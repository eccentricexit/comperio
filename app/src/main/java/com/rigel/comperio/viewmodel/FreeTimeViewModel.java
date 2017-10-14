package com.rigel.comperio.viewmodel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;

public class FreeTimeViewModel implements ViewModel {

    @NonNull
    private final Navigator navigator;

    public FreeTimeViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public void nextOnClick(){
        navigator.navigateToMainActivity();
    }
}
