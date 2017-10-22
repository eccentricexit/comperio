package com.rigel.comperio.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filter implements Serializable {

    @SerializedName("subject") public String subject;
    @SerializedName("maxDistance") public int maxDistance;
    @SerializedName("userLoc") public Float[] userLoc;

    @SerializedName("startHour") public Integer startHour;
    @SerializedName("startMinute") public Integer startMinute;
    @SerializedName("endHour") public Integer endHour;
    @SerializedName("endMinute") public Integer endMinute;

    @SerializedName("weekDaysAvailable") public String weekDaysAvailable;

    @SerializedName("initialized") public boolean initialized;
    @SerializedName("useMetricSystem") public boolean useMetricSystem;

    public Filter(){
        maxDistance = 2000;

        startHour = 8;
        startMinute = 0;

        endHour = 23;
        endMinute = 30;
    }

}
