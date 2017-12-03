package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.model.UserData;

public class FiltersViewModel extends BaseViewModel {

    public UserData userData;
    public Subject[] subjects;

    public FiltersViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                            LoggingManager logger) {
        super(navigator, persistenceManager, logger);

        userData = persistenceManager.loadUserData();
        subjects = DevUtils.getFakeSubjects();
    }

    public String getFormattedDistance() {
        return userData.maxDistance + "m";
    }

    public void save(View view) {
        persistenceManager.saveUserData(userData);
        persistenceManager.requestSync();
    }

}
