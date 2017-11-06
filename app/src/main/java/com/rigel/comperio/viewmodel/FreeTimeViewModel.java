package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Filter;

public class FreeTimeViewModel extends BaseViewModel {

    public Filter filter;

    public FreeTimeViewModel(Navigator navigator, PersistenceManager persistenceManager,
                             DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
        filter = persistenceManager.loadFilter();
    }

    public String getFormattedStartTime() {
        return filter.startHour + ":" + filter.startMinute;
    }

    public String getFormattedEndTime() {
        return filter.endHour + ":" + filter.endMinute;
    }


    public void nextOnClick(View view) {
        filter.initialized = true;

        persistenceManager.saveFilter(filter);
        navigator.navigateToHomeActivity();
    }

}
