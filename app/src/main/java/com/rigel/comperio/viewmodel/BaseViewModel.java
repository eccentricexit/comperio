package com.rigel.comperio.viewmodel;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;

import java.util.Observable;

public class BaseViewModel extends Observable {
    protected NavigationManager navigator;
    protected PersistenceManager persistenceManager;
    protected LoggingManager logger;

    public BaseViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                         LoggingManager logger) {
        this.navigator = navigator;
        this.persistenceManager = persistenceManager;
        this.logger = logger;
    }

}
