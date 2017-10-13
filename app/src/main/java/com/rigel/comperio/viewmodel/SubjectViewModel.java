package com.rigel.comperio.viewmodel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;


public class SubjectViewModel implements ViewModel {

    @NonNull
    private final Navigator navigator;

    public SubjectViewModel(Navigator navigator){
        this.navigator = navigator;
    }

    public void nextOnClick(){
        navigator.navigateToFreeTimeActivity();
    }
}
