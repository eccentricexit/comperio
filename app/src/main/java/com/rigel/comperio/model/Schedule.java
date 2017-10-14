package com.rigel.comperio.model;

import org.parceler.Parcel;

@Parcel
public class Schedule {

    String teacherName;
    String teacherStory;
    String teacherPhoneNumber;
    String distance;
    String rating;
    String hourPrice;
    String subject;
    String imageUrl;

    public Schedule() {
    }

    public Schedule(String teacherName, String rating, String hourPrice, String imageUrl,
                    String distance, String teacherPhoneNumber, String teacherStory, String subject) {

        this.teacherName = teacherName;
        this.rating = rating;
        this.hourPrice = hourPrice;
        this.imageUrl = imageUrl;
        this.distance = distance;
        this.teacherPhoneNumber = teacherPhoneNumber;
        this.teacherStory = teacherStory;
        this.subject = subject;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getTeacherStory() {
        return teacherStory;
    }

    public void setTeacherStory(String teacherStory) {
        this.teacherStory = teacherStory;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }
}
