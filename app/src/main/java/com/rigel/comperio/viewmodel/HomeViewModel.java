package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.util.List;

public class HomeViewModel extends SchedulesViewModel {

    public HomeViewModel(Navigator navigator) {
        super(navigator);
    }

    @Override
    protected List<Schedule> fetchSchedules() {
        return DevUtils.getFakeHomeSchedules();
    }
}
