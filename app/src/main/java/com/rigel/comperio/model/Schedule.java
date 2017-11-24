package com.rigel.comperio.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.rigel.comperio.data.ComperioContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements Serializable {

    @SerializedName("_id") public String _id;
    @SerializedName("subjectName") public String subjectName;
    @SerializedName("hourPrice") public Float hourPrice;
    @SerializedName("loc") public Float[] loc;

    @SerializedName("teacherName") public String teacherName;
    @SerializedName("teacherPicUrl") public String teacherPicUrl;
    @SerializedName("teacherPhone") public String teacherPhone;
    @SerializedName("teacherRating") public Float teacherRating;
    @SerializedName("teacherStory") public String teacherStory;

    @SerializedName("distance") public Float distance;

    public Schedule() {
        loc = new Float[2];
        hourPrice = 0f;
        teacherName = "";
        teacherStory = "";
        teacherPhone = "";
        subjectName = "";
    }

    public Schedule(String subjectName, Float hourPrice, Float[] loc,
                    String teacherName, String teacherPicUrl, String teacherPhone, Float teacherRating,
                    String teacherStory, Float distance) {

        this.subjectName = subjectName;
        this.hourPrice = hourPrice;
        this.loc = loc;
        this.teacherName = teacherName;
        this.teacherPicUrl = teacherPicUrl;
        this.teacherPhone = teacherPhone;
        this.teacherRating = teacherRating;
        this.teacherStory = teacherStory;
        this.distance = distance;
    }

    public static List<Schedule> schedulesFromCursor(Cursor cursor) {
        if(cursor.getCount()==0){
            return new ArrayList<>();
        }

        List<Schedule> schedules = new ArrayList<>();

        while (cursor.moveToNext()) {
            Schedule schedule = new Schedule();

            schedule._id = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_SCHEDULE_ID));
            schedule.subjectName = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_SUBJECT_NAME));
            schedule.hourPrice = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_HOUR_PRICE));
            schedule.loc[0] = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_LONG));
            schedule.loc[1] = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_LAT));
            schedule.teacherName = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_NAME));
            schedule.teacherPicUrl = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_PIC_URL));
            schedule.teacherPhone = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_PHONE));
            schedule.teacherRating = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_RATING));
            schedule.teacherStory = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_STORY));
            schedule.distance = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_DISTANCE));

            schedules.add(schedule);

        }

        return schedules;
    }
}