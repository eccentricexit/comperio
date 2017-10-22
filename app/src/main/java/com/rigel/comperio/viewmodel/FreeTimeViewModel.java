package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Filter;

public class FreeTimeViewModel extends BaseViewModel {

    public Filter filter;

    public FreeTimeViewModel(Navigator navigator, SettingsManager settingsManager) {
        super(navigator, settingsManager);
        filter = settingsManager.loadFilter();
    }

    public String getFormattedStartTime(){
        return filter.startHour+":"+filter.startMinute;
    }

    public String getFormattedEndTime(){
        return filter.endHour+":"+filter.endMinute;
    }


    public void nextOnClick(View view){
        filter.initialized = true;

        settingsManager.saveFilter(filter);
        navigator.navigateToMainActivity();
    }

}
