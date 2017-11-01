package com.rigel.comperio.viewmodel;

import android.content.ContentValues;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {

    List<Schedule> schedules;

    public HomeViewModel(Navigator navigator, PersistenceManager persistenceManager, DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);
        schedules = new ArrayList<>();
    }

    public void refreshItems() {
        schedules.clear();
        schedules.addAll(DevUtils.getFakeHomeSchedules());
        setChanged();
        notifyObservers();
    }

    public void addToFavorites(Schedule schedule){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ComperioContract.FavoriteEntry.COLUMN_SCHEDULE_KEY,schedule._id);

        persistenceManager.getContentResolver()
                .insert(ComperioContract.FavoriteEntry.CONTENT_URI,contentValues);
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }
}
