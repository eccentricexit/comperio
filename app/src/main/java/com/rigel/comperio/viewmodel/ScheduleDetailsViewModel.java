package com.rigel.comperio.viewmodel;

import android.util.Log;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailsViewModel implements ViewModel {
    private static final String LOG_TAG = ScheduleDetailsViewModel.class.getSimpleName();

    public Schedule schedule;


    public ScheduleDetailsViewModel(Schedule schedule) {
        this.schedule = schedule;
    }

    public void letsTalk() {
        Log.d(LOG_TAG, "IMPLEMENT add teacher to contacts");
    }
}
