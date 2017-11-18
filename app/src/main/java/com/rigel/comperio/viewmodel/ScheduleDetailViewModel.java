package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends BaseViewModel {

    public Schedule schedule;

    public ScheduleDetailViewModel(Navigator navigator, PersistenceManager persistenceManager,
                                   Schedule schedule, DevUtils.Logger logger) {

        super(navigator, persistenceManager, logger);
        this.schedule = schedule;
    }

    public void onLetsTalkClick(View view) {
        navigator.navigateToAddContact(schedule);
    }

    protected Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        notifyObservers();
    }

    public String getFormattedDistance() {
        return schedule.distance+"m";
    }

    public String getFormattedHourPrice(){
        return "$"+schedule.hourPrice+"/hour";
    }

}
