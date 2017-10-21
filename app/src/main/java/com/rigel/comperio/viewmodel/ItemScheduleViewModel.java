package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public class ItemScheduleViewModel extends ScheduleViewModel {

    public ItemScheduleViewModel(Schedule schedule, Navigator navigator) {
        super(schedule, navigator);
    }

    public void onItemClick(View view) {
        getNavigator().navigateToDetailsActivity(getSchedule());
    }

}
