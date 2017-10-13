package com.rigel.comperio.model;

public class Schedule {
    public String teacherName;
    public String distance;
    public String rating;
    public String hourPrice;
    public String imageUrl;

    public Schedule(String teacherName, String rating, String hourPrice, String imageUrl, String distance) {
        this.teacherName = teacherName;
        this.rating = rating;
        this.hourPrice = hourPrice;
        this.imageUrl = imageUrl;
        this.distance = distance;
    }


}
