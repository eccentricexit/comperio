package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Filter;

public class FiltersViewModel extends BaseViewModel {

    public Filter filter;

    public FiltersViewModel(Navigator navigator, PersistenceManager persistenceManager, DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
        filter = persistenceManager.loadFilter();
    }

    public String getFormattedDistance() {
        return filter.maxDistance + "m";
    }

    public void onPersistClick(View view) {
        persistenceManager.saveFilter(filter);
    }

}
