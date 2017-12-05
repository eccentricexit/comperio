package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.support.v4.app.ActivityOptionsCompat;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.model.Schedule;

public class ItemScheduleViewModel extends BaseObservable {

    public Schedule schedule;
    private NavigationManager navigator;
    private LoggingManager logger;

    public ItemScheduleViewModel(Schedule schedule, NavigationManager navigator,LoggingManager logger) {
        this.schedule = schedule;
        this.navigator = navigator;
        this.logger = logger;
    }

    public String getFormattedDistance() { return String.format("%.2f", schedule.distance/1000f) + "km";
    }

    public String getFormattedHourPrice(){
        return "$"+schedule.hourPrice+"/hour";
    }

    public void onItemTap(ActivityOptionsCompat options) {
        navigator.navigateToScheduleDetails(schedule,options);
    }
}
