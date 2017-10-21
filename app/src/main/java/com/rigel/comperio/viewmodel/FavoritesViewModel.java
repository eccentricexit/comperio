package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.util.List;

public class FavoritesViewModel extends SchedulesViewModel {


    public FavoritesViewModel(Navigator navigator) {
        super(navigator);
    }

    @Override
    protected List<Schedule> fetchSchedules() {
        return DevUtils.getFakeFavoritesSchedules();
    }
}
