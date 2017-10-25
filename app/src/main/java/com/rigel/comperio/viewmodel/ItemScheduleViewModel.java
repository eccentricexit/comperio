package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public class ItemScheduleViewModel extends BaseObservable {

    public Schedule schedule;

    private Navigator navigator;

    public ItemScheduleViewModel(Schedule schedule, Navigator navigator) {
        this.schedule = schedule;
        this.navigator = navigator;
    }

    public void onItemClick(View view) {
        navigator.navigateToDetailsActivity(schedule);
    }

    public String getFormattedDistance(){
        return 999 + "m";
    }

}
