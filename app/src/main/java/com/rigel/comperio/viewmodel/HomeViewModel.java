package com.rigel.comperio.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int SCHEDULES_LOADER = 1;
    private final Context context;
    private LoaderManager loaderManager;

    List<Schedule> schedules;

    public HomeViewModel(Navigator navigator, PersistenceManager persistenceManager,
                         DevUtils.Logger logger, LoaderManager loaderManager, Context context) {

        super(navigator, persistenceManager, logger);
        this.loaderManager = loaderManager;
        this.context = context;

        schedules = new ArrayList<>();
    }

    public void initViewModel() {
        initializeLoader();
    }

    public void refreshItems() {
        setChanged();
        notifyObservers();
    }

    private void initializeLoader() {
        loaderManager.initLoader(SCHEDULES_LOADER, null, this);
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                context,
                ComperioContract.ScheduleTable.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        schedules = Schedule.schedulesFromCursor(data);
        refreshItems();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        schedules.clear();
        refreshItems();
    }

    public void swiped(ItemScheduleViewModel itemScheduleViewModel) {
        persistenceManager.addToFavorites(itemScheduleViewModel.schedule);
    }

    public void onClickNewSchedule(View view){
        navigator.navigateToNewSchedule();
    }
}
