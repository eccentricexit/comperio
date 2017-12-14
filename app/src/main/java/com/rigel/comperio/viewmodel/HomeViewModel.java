package com.rigel.comperio.viewmodel;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int SCHEDULES_LOADER = 1;
    private final Context context;

    public boolean isConnectedToInternet;

    private LoaderManager loaderManager;
    private List<Schedule> schedules;

    public HomeViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                         LoggingManager logger, LoaderManager loaderManager, Context context) {

        super(navigator, persistenceManager, logger);
        this.loaderManager = loaderManager;
        this.context = context;

        schedules = new ArrayList<>();
    }

    public void refreshItems() {
        setChanged();
        notifyObservers();
    }

    public void initializeLoader() {
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

    public void addToFavorites(ItemScheduleViewModel itemScheduleViewModel) {
        persistenceManager.addToFavorites(itemScheduleViewModel.schedule);
        logger.toast(context.getResources().getString(R.string.msg_added_favorite));
    }

    public void onClickNewSchedule(View view){
        if(!isConnectedToInternet){
            logger.toast("No internet connection.");
            return;
        }
        navigator.navigateToNewSchedule();
    }
}
