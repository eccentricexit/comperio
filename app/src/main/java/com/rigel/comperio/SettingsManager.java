package com.rigel.comperio;

public interface SettingsManager {

    void saveSubject(String subject);

    void saveRecurrence(String rRule);

    void saveDistance(Integer distance);

    void saveUseMetricSystem(Boolean metricSystem);

    void saveStartHour(Integer startHour);

    void saveStartMinute(Integer startMinute);

    void saveEndHour(Integer endHour);

    void saveEndMinute(Integer endMinute);

    String getSubject();

    String getRecurrence();

    Integer getDistance();

    Boolean getUseMetricSystem();

    Integer getStartHour();

    Integer getStartMinute();

    Integer getEndHour();

    Integer getEndMinute();
}
