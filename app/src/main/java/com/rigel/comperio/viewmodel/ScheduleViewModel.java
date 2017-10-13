package com.rigel.comperio.viewmodel;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Action;

public class ScheduleViewModel implements ViewModel {

    public final Schedule schedule;
    public final Navigator navigator;

    public ScheduleViewModel(Schedule schedule, final Navigator navigator) {
        this.schedule = schedule;
        this.navigator = navigator;
    }

    public void onClick(){
        navigator.navigateToScheduleDetailsActivity();
    }


}
