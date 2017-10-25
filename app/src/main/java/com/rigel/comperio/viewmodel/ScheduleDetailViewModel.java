package com.rigel.comperio.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends BaseViewModel {

    public Schedule schedule;

    public ScheduleDetailViewModel(Navigator navigator, SettingsManager settingsManager,
                                   Schedule schedule, DevUtils.Logger logger) {

        super(navigator, settingsManager, logger);
        this.schedule = schedule;
    }

//    public ScheduleDetailViewModel(Navigator navigator, SettingsManager settingsManager,
//                                   Schedule schedule) {
//        super(navigator, settingsManager,);
//        this.schedule = schedule;
//    }

    public void onLetsTalkClick(View view) {
        // TODO: lauch intent to add teacher to contacts
    }

    protected Schedule getSchedule(){
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        notifyObservers();
    }

    public String getFormattedDistance(){
        return "2.3km";
    }

}
