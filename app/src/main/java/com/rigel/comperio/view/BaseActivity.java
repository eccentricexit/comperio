package com.rigel.comperio.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Schedule;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    private Navigator navigator;
    private PersistenceManager persistenceManager;
    private DevUtils.Logger logger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    protected Navigator getNavigator() {

        if(navigator != null){
            return navigator;
        }

        navigator = new Navigator() {
            @Override
            public void navigateToFreeTimeActivity() {
                navigate(FreeTimeActivity.class);
            }

            @Override
            public void navigateToHomeActivity() {
                navigate(MainActivity.class);
            }

            @Override
            public void navigateToDetailsActivity(Schedule schedule) {
                Intent intent = new Intent(BaseActivity.this, ScheduleDetailActivity.class);
                intent.putExtra(BaseActivity.this.getString(R.string.EXTRA_SCHEDULE), schedule);
                startActivity(intent);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };

        return navigator;
    }

    protected PersistenceManager getPersistenceManager() {
        if(persistenceManager !=null){
            return persistenceManager;
        }

        persistenceManager = new PersistenceManager() {

            @Override
            public Filter loadFilter() {
                Gson gson = new Gson();
                String json = getComperioPreferences()
                        .getString(getString(R.string.FILTER_KEY),"");

                if(!json.equals("")){
                    return gson.fromJson(json, Filter.class);
                }else{
                    return new Filter();
                }
            }

            @Override
            public void saveFilter(Filter filter) {
                String json = new Gson().toJson(filter);
                getEditor().putString(getString(R.string.FILTER_KEY), json).commit();
            }

            @Override
            public void addToFavorites(Schedule schedule) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ComperioContract.FavoriteEntry.COLUMN_SCHEDULE_KEY,schedule._id);

                getContentResolver()
                        .insert(ComperioContract.FavoriteEntry.CONTENT_URI,contentValues);
            }

            @Override
            public void removeFromFavorites(Schedule schedule) {
                getContentResolver().delete(
                        ComperioContract.FavoriteEntry.CONTENT_URI,
                        ComperioContract.FavoriteEntry.COLUMN_SCHEDULE_KEY + " = ?",
                        new String[]{schedule._id});
            }

            private SharedPreferences.Editor getEditor() {
                SharedPreferences sharedPref = getComperioPreferences();
                return sharedPref.edit();
            }

            private SharedPreferences getComperioPreferences() {
                String key = getString(R.string.SHARED_PREFERENCES_KEY);
                return getSharedPreferences(key,0);
            }
        };

        return persistenceManager;
    }

    protected DevUtils.Logger getLogger(){
        if(logger!=null){
            return logger;
        }

        logger = new DevUtils.Logger() {
            @Override
            public void log(String logMessage) {
                Timber.d(logMessage);
            }

            @Override
            public void toast(String message) {
                Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };

        return logger;
    }

    protected abstract void initDataBinding();
}
