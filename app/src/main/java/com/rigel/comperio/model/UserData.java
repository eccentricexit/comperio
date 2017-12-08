package com.rigel.comperio.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData implements Serializable {

    @SerializedName("subject") public String subject;
    @SerializedName("maxDistance") public Integer maxDistance;
    @SerializedName("userLoc") public Float[] userLoc;
    @SerializedName("initialized") public Boolean initialized;
    @SerializedName("suggestedSchedule") public Schedule suggestedSchedule;

    public UserData(){
        subject = "";
        maxDistance = 3500;
        userLoc = new Float[2];
        initialized = false;
    }

    @Override
    public String toString() {
        return "{subject: "+subject+", maxDistance: "+maxDistance+", userLoc[lon,lat]: "
                +userLoc[0]+","+userLoc[1]+", initialized: "+initialized+"}";
    }
}
