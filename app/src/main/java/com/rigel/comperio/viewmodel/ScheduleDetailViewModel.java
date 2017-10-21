package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel extends ScheduleViewModel {

    public ScheduleDetailViewModel(Schedule schedule, Navigator navigator) {
        super(schedule, navigator);
    }

    public void onLetsTalkClick(View view) {
        // TODO: lauch intent to add teacher to contacts
    }
}
