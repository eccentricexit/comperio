package com.rigel.comperio.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.rigel.comperio.R;
import com.rigel.comperio.SettingsManager;

public class FiltersActivity extends BottomNavigationActivity {

    @Override
    protected Context getContext() {
        return getContext();
    }



    protected SettingsManager getSettingsManager() {
        return new SettingsManager() {

            @Override
            public void saveSubject(Long subject) {
                getEditor().putLong(getString(R.string.pref_subject), subject).commit();
            }

            @Override
            public void saveRecurrence(String rRule) {
                getEditor().putString(getString(R.string.pref_recurrence), rRule).commit();
            }

            @Override
            public void saveDistance(Integer distance) {
                getEditor().putInt(getString(R.string.pref_distance), distance).commit();
            }

            @Override
            public void saveUseMetricSystem(Boolean metricSystem) {
                getEditor().putBoolean(getString(R.string.pref_metric), metricSystem).commit();
            }

            @Override
            public void saveStartHour(Integer startHour) {
                getEditor().putInt(getString(R.string.pref_startHour), startHour).commit();
            }

            @Override
            public void saveStartMinute(Integer startMinute) {
                getEditor().putInt(getString(R.string.pref_startMinute), startMinute).commit();
            }

            @Override
            public void saveEndHour(Integer endHour) {
                getEditor().putInt(getString(R.string.pref_endHour), endHour).commit();
            }

            @Override
            public void saveEndMinute(Integer endMinute) {
                getEditor().putInt(getString(R.string.pref_endMinute), endMinute).commit();
            }

            @Override
            public Long getSubject() {
                return getSharedPreferences().getLong(getString(R.string.pref_subject), 0);
            }

            @Override
            public String getRecurrence() {
                return getSharedPreferences().getString(getString(R.string.pref_recurrence), null);
            }

            @Override
            public Integer getDistance() {
                return getSharedPreferences().getInt(
                        getString(R.string.pref_distance), getResources().getInteger(R.integer.default_distance)
                );

            }

            @Override
            public Boolean getUseMetricSystem() {
                return getSharedPreferences().getBoolean(getString(R.string.pref_metric), false);
            }

            @Override
            public Integer getStartHour() {
                return getSharedPreferences().getInt(
                        getString(R.string.pref_startHour), getResources().getInteger(R.integer.default_StartHour)
                );
            }

            @Override
            public Integer getStartMinute() {
                return getSharedPreferences().getInt(
                        getString(R.string.pref_startMinute), getResources().getInteger(R.integer.default_StartMinute)
                );
            }

            @Override
            public Integer getEndHour() {
                return getSharedPreferences().getInt(
                        getString(R.string.pref_endHour), getResources().getInteger(R.integer.default_EndHour)
                );
            }

            @Override
            public Integer getEndMinute() {
                return getSharedPreferences().getInt(
                        getString(R.string.pref_startMinute), getResources().getInteger(R.integer.default_EndMinute)
                );
            }

            @Override
            public Boolean getPreferencesInitialized() {
                return getSharedPreferences().getBoolean(
                        getString(R.string.pref_initialized),
                        false
                );
            }

            @Override
            public void setPreferencesInitialized(boolean initialized) {
                getEditor().putBoolean(getString(R.string.pref_initialized), initialized).commit();
            }

            private SharedPreferences.Editor getEditor() {
                SharedPreferences sharedPref = getSharedPreferences();
                return sharedPref.edit();
            }

            private SharedPreferences getSharedPreferences() {
                return getPreferences(Context.MODE_PRIVATE);
            }

        };
    }

}
