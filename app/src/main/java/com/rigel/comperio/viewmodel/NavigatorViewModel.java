package com.rigel.comperio.viewmodel;

import com.rigel.comperio.Navigator;

import java.util.Observable;

public abstract class NavigatorViewModel extends Observable {
    protected Navigator navigator;

    public NavigatorViewModel(Navigator navigator){
        this.navigator = navigator;
    }

    protected Navigator getNavigator(){
        return this.navigator;
    }
}
