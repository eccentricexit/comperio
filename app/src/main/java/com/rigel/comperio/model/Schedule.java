package com.rigel.comperio.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Schedule implements Serializable{

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

    public Schedule(String subjectName, Float hourPrice, String weekDaysAvailable, Float[] loc,
                    String teacherName, String teacherPicUrl, String teacherPhone, Float teacherRating,
                    String teacherStory, Integer startHour, Integer startMinute, Integer endHour,
                    Integer endMinute) {

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
    }

}