package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends ScheduleViewModel {

    public ScheduleDetailViewModel(Schedule schedule, Navigator navigator) {
        super(schedule, navigator);
    }

    public void onLetsTalkClick(View view) {
        // TODO: lauch intent to add teacher to contacts
    }
}
