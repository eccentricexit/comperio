package com.rigel.comperio.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FiltersViewModel implements ViewModel {

    public final ObservableField<String> subject = new ObservableField<>("Spanish");

    public final ObservableField<Integer> distance = new ObservableField<>(2000); //in meters
    public final ObservableField<Integer> startHour = new ObservableField<>(18); //in meters
    public final ObservableField<Integer> startMinutes = new ObservableField<>(0); //in meters
    public final ObservableField<Integer> endHour = new ObservableField<>(22); //in meters
    public final ObservableField<Integer> endMinutes = new ObservableField<>(0); //in meters

    public final ObservableField<Boolean> useMetricSystem = new ObservableField<>(true);
    public final ObservableField<EventRecurrence> recurrence = new ObservableField<>(new EventRecurrence());

    public FiltersViewModel(){
        subject.set("Testytest");
    }

    public void setStartTime(int hour, int minute) {
        this.startHour.set(hour);
        this.startMinutes.set(minute);
    }

    public void setEndTime(int hour, int minute) {
        this.endHour.set(hour);
        this.endMinutes.set(minute);
    }

    public String getStartTime(){
        return startHour.get()+":"+startMinutes.get();
    }

    public String getEndTime(){
        return endHour.get()+":"+endMinutes.get();
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
}
