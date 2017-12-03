package com.rigel.comperio.viewmodel;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends BaseViewModel {

    private final AppCompatActivity callingActivity;
    public Schedule schedule;

    public ScheduleDetailViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                                   Schedule schedule, LoggingManager logger, AppCompatActivity callingActivity) {

        super(navigator, persistenceManager, logger);
        this.schedule = schedule;
        this.callingActivity = callingActivity;
    }

    public void onLetsTalkClick(View view) {
        navigator.navigateToAddContact(schedule,callingActivity);
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
