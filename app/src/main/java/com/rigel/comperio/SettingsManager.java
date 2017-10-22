package com.rigel.comperio;

import com.rigel.comperio.model.Filter;

public interface SettingsManager {

    Filter loadFilter();
    void saveFilter(Filter filter);

}
