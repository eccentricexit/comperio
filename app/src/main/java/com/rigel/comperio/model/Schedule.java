package com.rigel.comperio.model;

public class Schedule {
    String teacherName;
    String rating;
    String hourPrice;
    String imageUrl;

    public Schedule(String teacherName, String rating, String hourPrice, String imageUrl, String distance) {
        this.teacherName = teacherName;
        this.rating = rating;
        this.hourPrice = hourPrice;
        this.imageUrl = imageUrl;
        this.distance = distance;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(String hourPrice) {
        this.hourPrice = hourPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    String distance;


}
