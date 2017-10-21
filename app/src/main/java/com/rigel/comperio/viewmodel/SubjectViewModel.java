package com.rigel.comperio.viewmodel;

import android.databinding.BaseObservable;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Subject;


public class SubjectViewModel extends BaseObservable {

    private final Navigator navigator;
    private final SettingsManager settingsManager;

    public Subject[] subjects;
    public Subject subject;

    public SubjectViewModel(Navigator navigator, SettingsManager settingsManager) {
        this.navigator = navigator;
        this.settingsManager = settingsManager;
        this.subjects = new Subject[]{
                new Subject(0, "Spanish"),
                new Subject(1, "German"),
                new Subject(2, "French"),
                new Subject(3, "English"),
                new Subject(4, "Kotlin")
        };

        if (settingsManager.getPreferencesInitialized()) {
            navigator.navigateToMainActivity();
        }
    }

    public void nextOnClick() {
        saveToSharedPreferences();
        navigator.navigateToFreeTimeActivity();
    }

    private void saveToSharedPreferences() {
        settingsManager.saveSubject(subject.getId());
    }
}
