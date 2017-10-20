package com.rigel.comperio.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.view.ScheduleDetailActivity;

public class ItemScheduleViewModel extends BaseObservable {

    private Schedule schedule;
    private Context context; // TODO: swap for Navigator

    public ItemScheduleViewModel(Schedule schedule, Context navigator) {
        this.schedule = schedule;
        this.context = context;
    }

    public String getTeacherName() {
        return schedule.teacherName;
    }

    public String getTeacherPhone() {
        return schedule.teacherPhone.toString();
    }

    public String getTeacherPicUrl() {
        return schedule.teacherPicUrl;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        // TODO: add picasso loading
    }

    public void onItemClick(View view) {
        // TODO: add navigation
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        notifyChange();
    }
}
