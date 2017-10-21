package com.rigel.comperio.viewmodel;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;


public class SubjectViewModel extends NavigatorViewModel {

    private SettingsManager settingsManager;

    public SubjectViewModel(Navigator navigator, SettingsManager settingsManager) {
        super(navigator);
        this.settingsManager = settingsManager;
    }

    public void nextOnClick() {
        saveToSharedPreferences();
        navigator.navigateToFreeTimeActivity();
    }

    private void saveToSharedPreferences() {
        settingsManager.saveSubject(0l);
    }
}
