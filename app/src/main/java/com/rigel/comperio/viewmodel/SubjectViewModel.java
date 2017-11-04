package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Subject;


public class SubjectViewModel extends BaseViewModel {

    public Filter filter;
    public Subject[] subjects;

    public SubjectViewModel(Navigator navigator, PersistenceManager persistenceManager, DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
        filter = persistenceManager.loadFilter();
        subjects = DevUtils.getFakeSubjects();
        if (filter.initialized) {
            navigator.navigateToHomeActivity();
        }
    }

    public void nextOnClick(View view) {
        filter.initialized = true;
        persistenceManager.saveFilter(filter);
        navigator.navigateToFreeTimeActivity();
    }

}
