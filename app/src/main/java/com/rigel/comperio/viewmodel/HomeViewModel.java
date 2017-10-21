package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class HomeViewModel extends Observable implements Serializable {

    private List<Schedule> schedules;

    public HomeViewModel() {
        this.schedules = new ArrayList<>();
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
