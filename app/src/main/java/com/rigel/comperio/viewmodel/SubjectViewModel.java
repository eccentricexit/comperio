package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.model.UserData;


public class SubjectViewModel extends BaseViewModel {

    public UserData userData;
    public Subject[] subjects;

    public SubjectViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                            LoggingManager logger) {
        super(navigator, persistenceManager, logger);

        userData = persistenceManager.loadUserData();
        subjects = DevUtils.getFakeSubjects();
        if (userData.initialized && userData.subject!=null && !userData.subject.equals("")) {
            navigator.navigateToHomeActivity();
        }
    }

    public void nextOnClick(View view) {
        userData.initialized = true;
        persistenceManager.saveUserData(userData);
        navigator.navigateToHomeActivity();
    }

}
