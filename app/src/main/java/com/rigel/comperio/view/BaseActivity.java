package com.rigel.comperio.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.model.Schedule;

import org.parceler.Parcels;

public abstract class BaseActivity extends MvvmActivity {

    protected SettingsManager getSettingsManager() {
        return new SettingsManager() {

            @Override
            public void saveSubject(String subject) {
                getEditor().putString(getString(R.string.pref_subject), subject).commit();
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
            public String getSubject() {
                return getSharedPreferences().getString(getString(R.string.pref_subject), null);
            }

            @Override
            public String getRecurrence() {
                return getSharedPreferences().getString(getString(R.string.pref_recurrence), null);
            }

            @Override
            public Integer getDistance() {
                return getSharedPreferences().getInt(getString(R.string.pref_distance), -1);
            }

            @Override
            public Boolean getUseMetricSystem() {
                return getSharedPreferences().getBoolean(getString(R.string.pref_metric), false);
            }

            @Override
            public Integer getStartHour() {
                return getSharedPreferences().getInt(getString(R.string.pref_startHour), -1);
            }

            @Override
            public Integer getStartMinute() {
                return getSharedPreferences().getInt(getString(R.string.pref_startMinute), -1);
            }

            @Override
            public Integer getEndHour() {
                return getSharedPreferences().getInt(getString(R.string.pref_endHour), -1);
            }

            @Override
            public Integer getEndMinute() {
                return getSharedPreferences().getInt(getString(R.string.pref_endMinute), -1);
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

    @NonNull
    protected Navigator getNavigator() {
        return new Navigator() {

            @Override
            public void navigateToFreeTimeActivity() {
                navigate(FreeTimeActivity.class);
            }

            @Override
            public void navigateToMainActivity() {
                navigate(MainActivity.class);
            }

            @Override
            public void navigateToScheduleDetailsActivity(Schedule schedule) {
                Intent i = new Intent(BaseActivity.this, ScheduleDetailActivity.class);
                i.putExtra(
                        BaseActivity.this.getString(R.string.EXTRA_SCHEDULE_ID),
                        Parcels.wrap(schedule)
                );

                startActivity(i);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };
    }


}
