package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;

import java.util.Observable;

public class BaseViewModel  extends Observable {
    protected Navigator navigator;
    protected SettingsManager settingsManager;
    protected DevUtils.Logger logger;

    public BaseViewModel(Navigator navigator, SettingsManager settingsManager) {
        this.navigator = navigator;
        this.settingsManager = settingsManager;
    }

    protected void setLogger(DevUtils.Logger logger){
        this.logger = logger;
    }

}
