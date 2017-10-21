package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

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
