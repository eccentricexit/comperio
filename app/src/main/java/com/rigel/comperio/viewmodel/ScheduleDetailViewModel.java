package com.rigel.comperio.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends BaseViewModel {

    public Schedule schedule;

    public ScheduleDetailViewModel(Navigator navigator, SettingsManager settingsManager, Schedule schedule) {
        super(navigator, settingsManager);
        this.schedule = schedule;
    }

    public void onLetsTalkClick(View view) {
        // TODO: lauch intent to add teacher to contacts
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        // TODO: add picasso loading
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


    public String getDistance(){
        return "2.4km";
    }
}
