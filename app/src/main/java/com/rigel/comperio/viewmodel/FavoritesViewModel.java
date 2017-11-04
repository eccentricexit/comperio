package com.rigel.comperio.viewmodel;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class FavoritesViewModel extends BaseViewModel {

    List<Schedule> schedules;

    public FavoritesViewModel(Navigator navigator, PersistenceManager persistenceManager,
                              DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
        schedules = new ArrayList<>();
    }

    public void refreshItems() {
        schedules.clear();
        schedules.addAll(DevUtils.getFakeFavoritesSchedules());
        setChanged();
        notifyObservers();
    }

    public void removeFromFavorites(Schedule schedule){
        persistenceManager.getContentResolver().delete(
                ComperioContract.FavoriteEntry.CONTENT_URI,
                ComperioContract.FavoriteEntry.COLUMN_SCHEDULE_KEY + " = ?",
                new String[]{schedule._id});

        refreshItems();
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }
}
