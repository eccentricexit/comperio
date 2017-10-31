package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public class ItemScheduleViewModel extends BaseObservable {

    public Schedule schedule;
    private Navigator navigator;
    private DevUtils.Logger logger;

    public ItemScheduleViewModel(Schedule schedule, Navigator navigator, DevUtils.Logger logger) {
        this.schedule = schedule;
        this.navigator = navigator;
        this.logger = logger;
    }

    public void onItemClick(View view) {
        navigator.navigateToDetailsActivity(schedule);
    }

    public String getFormattedDistance(){
        return 999 + "m";
    }

}
