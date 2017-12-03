package com.rigel.comperio.viewmodel;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                        LoggingManager logger) {
        super(navigator, persistenceManager, logger);
    }
}
