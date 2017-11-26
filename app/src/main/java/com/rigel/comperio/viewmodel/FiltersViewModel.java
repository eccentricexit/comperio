package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Subject;

public class FiltersViewModel extends BaseViewModel {

    public Filter filter;
    public Subject[] subjects;

    public FiltersViewModel(Navigator navigator, PersistenceManager persistenceManager, DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);

        filter = persistenceManager.loadFilter();
        subjects = DevUtils.getFakeSubjects();
    }

    public String getFormattedDistance() {
        return filter.maxDistance + "m";
    }

    public void save(View view) {
        persistenceManager.saveFilter(filter);
        persistenceManager.requestSync();
    }

}
