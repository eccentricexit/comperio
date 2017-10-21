package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends java.util.Observable implements Serializable {

    private List<Schedule> schedules;

    public FavoritesViewModel() {
        this.schedules = new ArrayList<>();
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
