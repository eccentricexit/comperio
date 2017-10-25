package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {

    List<Schedule> schedules;

    public HomeViewModel(Navigator navigator, SettingsManager settingsManager, DevUtils.Logger logger) {
        super(navigator, settingsManager, logger);
        schedules = new ArrayList<>();
    }


    public void refreshItems() {
        schedules.addAll(DevUtils.getFakeHomeSchedules());
        setChanged();
        notifyObservers();
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }
}
