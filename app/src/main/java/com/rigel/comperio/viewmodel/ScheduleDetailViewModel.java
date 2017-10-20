package com.rigel.comperio.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.rigel.comperio.model.Schedule;

public class ScheduleDetailViewModel {
    private Schedule schedule;

    public ScheduleDetailViewModel(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getFullUserName() {
        return schedule.teacherName;
    }

    public String getTeacherPhone() {
        return schedule.teacherPhone.toString();
    }

    public String getPictureProfile() {
        return schedule.teacherPicUrl;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        // TODO: Load with picasso
    }

    public void letsTalk(){
        // TODO: Broadcas intent to add teacher to contacts
    }
}
