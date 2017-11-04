package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.sync.SyncAdapter;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(Navigator navigator, PersistenceManager persistenceManager,
                         DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
    }
}
