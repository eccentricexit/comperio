package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends BaseViewModel {

    List<Schedule> schedules;

    public FavoritesViewModel(Navigator navigator, SettingsManager settingsManager) {
        super(navigator, settingsManager);
        schedules = new ArrayList<>();
    }

    public void refreshItems() {
        schedules.addAll(DevUtils.getFakeFavoritesSchedules());
        setChanged();
        notifyObservers();
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }
}
