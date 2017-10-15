package com.rigel.comperio.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Subject;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FiltersViewModel implements ViewModel {

    private static final String LOG_TAG = FiltersViewModel.class.getSimpleName();

    public final ObservableField<Long> subject = new ObservableField<>();
    public final ObservableField<Integer> distance = new ObservableField<>(0); //in meters
    public final ObservableField<Boolean> useMetricSystem = new ObservableField<>(true);
    public final ObservableField<EventRecurrence> recurrence = new ObservableField<>(new EventRecurrence());

    public final ObservableField<Integer> startHour = new ObservableField<>(0);
    public final ObservableField<Integer> startMinute = new ObservableField<>(0);
    public final ObservableField<Integer> endHour = new ObservableField<>(0);
    public final ObservableField<Integer> endMinute = new ObservableField<>(0);

    @NonNull
    private final SettingsManager settingsManager;
    public Subject[] subjects;

    public FiltersViewModel(@NonNull SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
        this.subjects = new Subject[]{
                new Subject(0, "Spanish"),
                new Subject(1, "German"),
                new Subject(2, "French"),
                new Subject(3, "English"),
                new Subject(4, "Kotlin")
        };

        subject.set(settingsManager.getSubject());
        startHour.set(settingsManager.getStartHour());
        startMinute.set(settingsManager.getStartMinute());
        endHour.set(settingsManager.getEndHour());
        endMinute.set(settingsManager.getEndMinute());
        distance.set(settingsManager.getDistance());
        useMetricSystem.set(settingsManager.getUseMetricSystem());
    }

    public void setStartTime(int hour, int minute) {
        this.startHour.set(hour);
        this.startMinute.set(minute);
    }

    public void setEndTime(int hour, int minute) {
        this.endHour.set(hour);
        this.endMinute.set(minute);
    }

    public String getStartTime(){
        return startHour.get() + ":" + startMinute.get();
    }

    public String getEndTime(){
        return endHour.get() + ":" + endMinute.get();
    }

    public void setRecurrence(EventRecurrence recurrence) {
        this.recurrence.set(recurrence);
    }

    public String getDistanceLabel(){
        int distanceInMeters = distance.get();
        float distanceInKiloMeters = distanceInMeters/1000f;
        float distance = useMetricSystem.get()?distanceInKiloMeters:distanceInKiloMeters*0.6213712f;

        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);

        String truncatedDistance = df.format(distance);

        return "Distance: "+truncatedDistance+(useMetricSystem.get()?"km":"miles");
    }

    public void persistSettings() {
        Log.d(LOG_TAG, "persisting settings");
        Log.d(LOG_TAG, "saving subject id:" + subject.get());
        settingsManager.saveSubject(subject.get());
        settingsManager.saveRecurrence(recurrence.get().toString());
        settingsManager.saveDistance(distance.get());
        settingsManager.saveUseMetricSystem(useMetricSystem.get());

        settingsManager.saveStartHour(startHour.get());
        settingsManager.saveStartMinute(startMinute.get());
        settingsManager.saveEndHour(endHour.get());
        settingsManager.saveEndMinute(endMinute.get());

        Log.d(LOG_TAG, "get subject id:" + settingsManager.getSubject());
    }

    public Integer getStartHour() {
        return startHour.get();
    }

    public Integer getStartMinute() {
        return startMinute.get();
    }

    public Integer getEndHour() {
        return endHour.get();
    }

    public Integer getEndMinute() {
        return endMinute.get();
    }

    @NonNull
    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}
