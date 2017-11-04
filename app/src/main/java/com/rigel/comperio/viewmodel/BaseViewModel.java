package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;

import java.util.Observable;

public class BaseViewModel extends Observable {
    protected Navigator navigator;
    protected PersistenceManager persistenceManager;
    protected DevUtils.Logger logger;

    public BaseViewModel(Navigator navigator, PersistenceManager persistenceManager, DevUtils.Logger logger) {
        this.navigator = navigator;
        this.persistenceManager = persistenceManager;
        this.logger = logger;
    }

}
