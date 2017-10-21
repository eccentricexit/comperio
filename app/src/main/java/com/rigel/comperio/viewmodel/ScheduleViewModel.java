package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;


public class ScheduleViewModel extends BaseObservable {

    private Schedule schedule;
    private Navigator navigator;

    public ScheduleViewModel(Schedule schedule, Navigator navigator) {
        this.schedule = schedule;
        this.navigator = navigator;
    }

    protected Schedule getSchedule(){
        return this.schedule;
    }

    protected Navigator getNavigator(){
        return this.navigator;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        // TODO: add picasso loading
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
        notifyChange();
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

    public String getTeacherRating(){return schedule.teacherRating.toString();}

    public String getHourPrice(){
        return schedule.hourPrice.toString();
    }

    public String getDistance(){
        return "calculate distance";
    }

    public String getSubjectName(){return schedule.subjectName;}

    public String getTeacherStory(){return schedule.teacherStory;}

    public String getStartTime(){return ""+schedule.startHour+":"+schedule.startMinute;}

    public String getEndTime(){return ""+schedule.endHour+":"+schedule.endMinute;}

}
