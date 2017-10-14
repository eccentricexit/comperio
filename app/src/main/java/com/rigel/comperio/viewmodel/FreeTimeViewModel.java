package com.rigel.comperio.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;

public class FreeTimeViewModel implements ViewModel {

    public final ObservableField<Integer> startHour = new ObservableField<>(0);
    public final ObservableField<Integer> startMinute = new ObservableField<>(0);
    public final ObservableField<Integer> endHour = new ObservableField<>(0);
    public final ObservableField<Integer> endMinutes = new ObservableField<>(0);
    public final ObservableField<EventRecurrence> recurrence = new ObservableField<>(new EventRecurrence());

    @NonNull
    private final Navigator navigator;
    @NonNull
    private final SettingsManager settingsManager;

    public FreeTimeViewModel(Navigator navigator, SettingsManager settingsManager) {
        this.navigator = navigator;
        this.settingsManager = settingsManager;

        startHour.set(settingsManager.getStartHour());
        startMinute.set(settingsManager.getStartMinute());
        endHour.set(settingsManager.getEndHour());
        endMinutes.set(settingsManager.getEndMinute());
    }

    public void nextOnClick(){
        saveToSharedPreferences();
        navigator.navigateToMainActivity();
    }

    private void saveToSharedPreferences() {
        settingsManager.saveStartHour(startHour.get());
        settingsManager.saveStartMinute(startMinute.get());
        settingsManager.saveEndHour(endHour.get());
        settingsManager.saveEndMinute(endMinutes.get());
        settingsManager.saveRecurrence("" + recurrence.get().byweeknoCount);
    }

    public void setStartTime(int hour, int minute) {
        this.startHour.set(hour);
        this.startMinute.set(minute);
    }

    public void setEndTime(int hour, int minute) {
        this.endHour.set(hour);
        this.endMinutes.set(minute);
    }

    public String getStartTime() {
        return startHour.get() + ":" + startMinute.get();
    }

    public String getEndTime() {
        return endHour.get() + ":" + endMinutes.get();
    }

    public void setRecurrence(EventRecurrence recurrence) {
        this.recurrence.set(recurrence);
    }

}
