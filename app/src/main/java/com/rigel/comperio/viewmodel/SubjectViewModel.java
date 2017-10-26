package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Subject;


public class SubjectViewModel extends BaseViewModel {

    public Filter filter;
    public Subject[]  subjects;

    public SubjectViewModel(Navigator navigator, SettingsManager settingsManager, DevUtils.Logger logger) {
        super(navigator, settingsManager, logger);
        filter = settingsManager.loadFilter();
        subjects = DevUtils.getFakeSubjects();
    }

    public void nextOnClick(View view) {
        settingsManager.saveFilter(filter);
        navigator.navigateToFreeTimeActivity();
    }

}
