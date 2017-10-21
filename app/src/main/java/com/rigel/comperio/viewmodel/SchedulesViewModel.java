package com.rigel.comperio.viewmodel;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public abstract class SchedulesViewModel extends NavigatorViewModel {

    private List<Schedule> schedules;

    public SchedulesViewModel(Navigator navigator) {
        super(navigator);
        this.schedules = new ArrayList<>();
    }

    public void refreshItems() {
        schedules.addAll(fetchSchedules());
        setChanged();
        notifyObservers();
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }

    protected abstract List<Schedule> fetchSchedules();
}
