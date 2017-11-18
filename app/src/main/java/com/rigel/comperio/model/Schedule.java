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
    @SerializedName("weekDaysAvailable") public String weekDaysAvailable;
    @SerializedName("loc") public Float[] loc;

    @SerializedName("teacherName") public String teacherName;
    @SerializedName("teacherPicUrl") public String teacherPicUrl;
    @SerializedName("teacherPhone") public String teacherPhone;
    @SerializedName("teacherRating") public Float teacherRating;
    @SerializedName("teacherStory") public String teacherStory;

    @SerializedName("startHour") public Integer startHour;
    @SerializedName("startMinute") public Integer startMinute;
    @SerializedName("endHour") public Integer endHour;
    @SerializedName("endMinute") public Integer endMinute;
    @SerializedName("distance") public Float distance;

    public Schedule() {
        loc = new Float[2];
    }

    public Schedule(String subjectName, Float hourPrice, String weekDaysAvailable, Float[] loc,
                    String teacherName, String teacherPicUrl, String teacherPhone, Float teacherRating,
                    String teacherStory, Integer startHour, Integer startMinute, Integer endHour,
                    Integer endMinute, Float distance) {

        this.subjectName = subjectName;
        this.hourPrice = hourPrice;
        this.weekDaysAvailable = weekDaysAvailable;
        this.loc = loc;
        this.teacherName = teacherName;
        this.teacherPicUrl = teacherPicUrl;
        this.teacherPhone = teacherPhone;
        this.teacherRating = teacherRating;
        this.teacherStory = teacherStory;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.distance = distance;
    }

    public static List<Schedule> schedulesFromCursor(Cursor cursor) {
        List<Schedule> schedules = new ArrayList<>();

        while (cursor.moveToNext()) {
            Schedule schedule = new Schedule();

            schedule.subjectName = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_SUBJECT_NAME));
            schedule.hourPrice = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_HOUR_PRICE));
            schedule.weekDaysAvailable = cursor.getString(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_WEEK_DAYS));
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
            schedule.startHour = cursor.getInt(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_START_HOUR));
            schedule.startMinute = cursor.getInt(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_START_MINUTE));
            schedule.endHour = cursor.getInt(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_END_HOUR));
            schedule.endMinute = cursor.getInt(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_END_MINUTE));
            schedule.distance = cursor.getFloat(
                    cursor.getColumnIndex(ComperioContract.ScheduleEntry.COLUMN_TEACHER_DISTANCE));

            schedules.add(schedule);

        }

        cursor.close();

        return schedules;
    }
}