package com.rigel.comperio;

import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Schedule;

public interface PersistenceManager {

    Filter loadFilter();
    void saveFilter(Filter filter);

    void addToFavorites(Schedule schedule);
    void removeFromFavorites(Schedule schedule);

}
