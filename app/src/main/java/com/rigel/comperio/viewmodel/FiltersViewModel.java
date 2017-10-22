package com.rigel.comperio.viewmodel;

import android.view.View;

import com.google.gson.Gson;
import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Filter;

public class FiltersViewModel extends BaseViewModel{

    public Filter filter;

    public FiltersViewModel(Navigator navigator, SettingsManager settingsManager) {
        super(navigator, settingsManager);
        filter = settingsManager.loadFilter();
    }

    public String getFormattedDistance(){
        return filter.maxDistance+"m";
    }

    public String getFormattedStartTime(){
        return filter.startHour+":"+filter.startMinute;
    }

    public String getFormattedEndTime(){
        return filter.endHour+":"+filter.endMinute;
    }

    public void onPersistClick(View view) {
        settingsManager.saveFilter(filter);
    }

}
