package com.rigel.comperio.viewmodel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;

public class FiltersViewModel implements ViewModel {
    @NonNull
    private final Navigator navigator;

    public FiltersViewModel(Navigator navigator){
        this.navigator = navigator;
    }
}
