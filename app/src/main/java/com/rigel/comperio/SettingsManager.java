package com.rigel.comperio;

public interface SettingsManager {

    void saveSubject(Long subject);

    void saveRecurrence(String rRule);

    void saveDistance(Integer distance);

    void saveUseMetricSystem(Boolean metricSystem);

    void saveStartHour(Integer startHour);

    void saveStartMinute(Integer startMinute);

    void saveEndHour(Integer endHour);

    void saveEndMinute(Integer endMinute);

    Long getSubject();

    String getRecurrence();

    Integer getDistance();

    Boolean getUseMetricSystem();

    Integer getStartHour();

    Integer getStartMinute();

    Integer getEndHour();

    Integer getEndMinute();

    Boolean getPreferencesInitialized();

    void setPreferencesInitialized(boolean initialized);


}
