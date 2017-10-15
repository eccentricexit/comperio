package com.rigel.comperio.model;

import org.parceler.Parcel;

@Parcel
public class Schedule {
    private String _id;
    private String subjectName;
    private Float hourPrice;
    private String weekDaysAvailable;
    private Float[] loc;

    private String teacherName;
    private String teacherPicUrl;
    private Integer teacherPhone;
    private Float teacherRating;

    private Integer startHour;
    private Integer startMinute;
    private Integer endHour;
    private Integer endMinute;

    public Integer getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(Integer startMinute) {
        this.startMinute = startMinute;
    }

    public Float getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Float hourPrice) {
        this.hourPrice = hourPrice;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherPicUrl() {
        return teacherPicUrl;
    }

    public void setTeacherPicUrl(String teacherPicUrl) {
        this.teacherPicUrl = teacherPicUrl;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWeekDaysAvailable() {
        return weekDaysAvailable;
    }

    public void setWeekDaysAvailable(String weekDaysAvailable) {
        this.weekDaysAvailable = weekDaysAvailable;
    }

    public Integer getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(Integer endMinute) {
        this.endMinute = endMinute;
    }

    public Float[] getLoc() {
        return loc;
    }

    public void setLoc(Float[] loc) {
        this.loc = loc;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(Integer teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Float getTeacherRating() {
        return teacherRating;
    }

    public void setTeacherRating(Float teacherRating) {
        this.teacherRating = teacherRating;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "ClassPojo [startMinute = " + startMinute + ", hourPrice = " + hourPrice +
                ", subjectName = " + subjectName + ", teacherPicUrl = " + teacherPicUrl +
                ", _id = " + _id + ", weekDaysAvailable = " + weekDaysAvailable + ", endMinute = " +
                endMinute + ", loc = " + loc + ", startHour = " + startHour + ", teacherPhone = " +
                teacherPhone + ", teacherRating = " + teacherRating + ", endHour = " + endHour +
                ", teacherName = " + teacherName + "]";
    }
}