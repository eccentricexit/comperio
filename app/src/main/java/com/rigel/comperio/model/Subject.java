package com.rigel.comperio.model;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("_id") public long _id;
    @SerializedName("name") public String name;

    public Subject(int _id, String name) {
        this._id = _id;
        this.name = name;
    }
}
