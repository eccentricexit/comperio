package com.rigel.comperio;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.google.gson.Gson;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.UserData;
import com.rigel.comperio.sync.ComperioService;
import com.rigel.comperio.sync.SyncAdapter;

import retrofit2.Call;

import static com.rigel.comperio.sync.SyncAdapter.getContentValuesFrom;

public class PersistenceManager {

    ComperioService comperioService;
    Context context;

    public PersistenceManager(Context context){
        this.context = context;
    }
    
    public UserData loadUserData() {
        Gson gson = new Gson();
        String json = getComperioPreferences()
                .getString(context.getString(R.string.FILTER_KEY), "");

        if (!json.equals("")) {
            return gson.fromJson(json, UserData.class);
        } else {
            return null;
        }
    }

    
    public void saveUserData(UserData userData) {
        String json = new Gson().toJson(userData);
        getEditor().putString(context.getString(R.string.FILTER_KEY), json).commit();
    }
    
    public void addToFavorites(Schedule schedule) {
        if(alreadyInFavorites(schedule)){
            return;
        }

        ContentValues contentValues = getContentValuesFrom(schedule);

        context.getContentResolver()
                .insert(ComperioContract.FavoriteTable.CONTENT_URI, contentValues);
    }
    
    public void removeFromFavorites(Schedule schedule) {
        context.getContentResolver().delete(
                ComperioContract.FavoriteTable.CONTENT_URI,
                ComperioContract.FavoriteTable.COLUMN_SCHEDULE_ID + " = ?",
                new String[]{schedule._id});
    }
    
    public void requestSync() {
        SyncAdapter.syncImmediately(context);
    }

    public Call<Schedule> publishNewSchedule(Schedule schedule) {

        if(schedule.loc[0]==null || schedule.loc[1]==null){
            schedule.loc[0]=0f;
            schedule.loc[1]=0f;
        }

        return getComperioService().publishNewSchedule(schedule.teacherName,
                DevUtils.getFakeUrl(),
                DevUtils.getFakeRating(),
                schedule.teacherPhone,
                schedule.subjectName,
                schedule.loc,
                schedule.hourPrice,
                schedule.teacherStory);

    }

    private ComperioService getComperioService(){
        if(comperioService==null){
            comperioService = ComperioApplication.get(context)
                    .getComperioService();
        }

        return  comperioService;
    }

    private boolean alreadyInFavorites(Schedule schedule) {
        Cursor cursor = context.getContentResolver().query(
                ComperioContract.FavoriteTable.CONTENT_URI,
                null,
                ComperioContract.FavoriteTable.COLUMN_SCHEDULE_ID + " = ? ",
                new String[]{schedule._id},
                null
        );
        int count = cursor.getCount();
        cursor.close();

        return count>0;
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences sharedPref = getComperioPreferences();
        return sharedPref.edit();
    }

    private SharedPreferences getComperioPreferences() {
        String key = context.getString(R.string.SHARED_PREFERENCES_KEY);
        return context.getSharedPreferences(key, 0);
    }
}
