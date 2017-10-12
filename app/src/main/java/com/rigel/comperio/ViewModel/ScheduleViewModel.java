package com.rigel.comperio.ViewModel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;

public class ScheduleViewModel implements ViewModel {

    @NonNull
    private final Navigator navigator;

    public ScheduleViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public void nextOnClick(){
        navigator.navigateToMainActivity();
    }
}
