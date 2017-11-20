package com.rigel.comperio.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filter implements Serializable {

    @SerializedName("subject") public String subject;
    @SerializedName("maxDistance") public int maxDistance;
    @SerializedName("userLoc") public Float[] userLoc;
    @SerializedName("initialized") public boolean initialized;
    @SerializedName("useMetricSystem") public boolean useMetricSystem;

    public Filter(){
        maxDistance = 2000;
    }

}
