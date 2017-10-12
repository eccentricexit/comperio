package com.rigel.comperio.viewmodel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel implements ViewModel {
    @NonNull
    private final Navigator navigator;

    public HomeViewModel(Navigator navigator){
        this.navigator = navigator;
    }


}
