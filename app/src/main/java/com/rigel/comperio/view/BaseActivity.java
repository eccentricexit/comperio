package com.rigel.comperio.view;

import android.content.Context;
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
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Filter;
import com.rigel.comperio.model.Schedule;

import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    private Navigator navigator;
    private SettingsManager settingsManager;
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
            public void navigateToMainActivity() {
                navigate(HomeActivity.class);
            }

            @Override
            public void navigateToFavoritesActivity() {
                navigate(FavoritesActivity.class);
            }

            @Override
            public void navigateToFiltersActivity() {
                navigate(FiltersActivity.class);
            }

            @Override
            public void navigateToDetailsActivity(Schedule schedule) {
                ScheduleDetailActivity.launch(BaseActivity.this,schedule);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };

        return navigator;
    }

    protected SettingsManager getSettingsManager() {
        if(settingsManager!=null){
            return settingsManager;
        }

        settingsManager = new SettingsManager() {


            @Override
            public Filter loadFilter() {

                Gson gson = new Gson();
                String json = getSharedPreferences().getString(getString(R.string.SHARED_PREF_KEY),"");
                Filter filter = !json.equals("")?gson.fromJson(json, Filter.class):new Filter();
                return filter;
            }

            @Override
            public void saveFilter(Filter filter) {
                String json = new Gson().toJson(filter);
                getEditor().putString(getString(R.string.SHARED_PREF_KEY), json);
                getEditor().commit();
            }

            private SharedPreferences.Editor getEditor() {
                SharedPreferences sharedPref = getSharedPreferences();
                return sharedPref.edit();
            }

            private SharedPreferences getSharedPreferences() {
                return getPreferences(Context.MODE_PRIVATE);
            }

        };

        return settingsManager;
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
