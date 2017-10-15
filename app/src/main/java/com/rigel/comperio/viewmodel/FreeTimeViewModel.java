package com.rigel.comperio.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;

public class FreeTimeViewModel implements ViewModel {

    private static final String LOG_TAG = FreeTimeViewModel.class.getSimpleName();

    public final ObservableField<Integer> startHour = new ObservableField<>(0);
    public final ObservableField<Integer> startMinute = new ObservableField<>(0);
    public final ObservableField<Integer> endHour = new ObservableField<>(0);
    public final ObservableField<Integer> endMinute = new ObservableField<>(0);
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
        endMinute.set(settingsManager.getEndMinute());
    }

    public void nextOnClick(){
        saveToSharedPreferences();
        navigator.navigateToMainActivity();
    }

    private void saveToSharedPreferences() {
        Log.d(LOG_TAG, "current startTime: " + settingsManager.getStartHour() + ":" + settingsManager.getStartMinute());
        Log.d(LOG_TAG, "current endTime: " + settingsManager.getEndHour() + ":" + settingsManager.getEndMinute());

        Log.d(LOG_TAG, "saving startTime: " + startHour.get() + ":" + startMinute.get());
        Log.d(LOG_TAG, "saving endTime: " + endHour.get() + ":" + endMinute.get());

        settingsManager.saveStartHour(startHour.get());
        settingsManager.saveStartMinute(startMinute.get());
        settingsManager.saveEndHour(endHour.get());
        settingsManager.saveEndMinute(endMinute.get());


        settingsManager.setPreferencesInitialized(true);

        Log.d(LOG_TAG, "getPreferencesInitialized:" + settingsManager.getPreferencesInitialized());

        Log.d(LOG_TAG, "saved startTime: " + settingsManager.getStartHour() + ":" + settingsManager.getStartMinute());
        Log.d(LOG_TAG, "saved endTime: " + settingsManager.getEndHour() + ":" + settingsManager.getEndMinute());
    }

    public void setStartTime(int hour, int minute) {
        this.startHour.set(hour);
        this.startMinute.set(minute);
    }

    public void setEndTime(int hour, int minute) {
        this.endHour.set(hour);
        this.endMinute.set(minute);
    }

    public String getStartTime() {
        return startHour.get() + ":" + startMinute.get();
    }

    public String getEndTime() {
        return endHour.get() + ":" + endMinute.get();
    }

    public void setRecurrence(EventRecurrence recurrence) {
        this.recurrence.set(recurrence);
    }

}
