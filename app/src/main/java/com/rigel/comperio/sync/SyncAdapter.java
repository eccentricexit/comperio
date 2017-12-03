package com.rigel.comperio.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.rigel.comperio.ComperioApplication;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.data.ComperioContract.ScheduleTable;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.UserData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String LOG_TAG = SyncAdapter.class.getSimpleName();

    private Context context;

    public SyncAdapter(Context context) {
        super(context, true);
        this.context = context;
    }

    private static Account getSyncAccount(Context context) {

        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        if (accountManager.getPassword(newAccount) == null) {
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }

            onAccountCreated(newAccount);
        }

        return newAccount;

    }

    private static void onAccountCreated(Account newAccount) {
        ContentResolver.setIsSyncable(newAccount, ComperioContract.CONTENT_AUTHORITY, 1);
        ContentResolver.setSyncAutomatically(newAccount, ComperioContract.CONTENT_AUTHORITY, true);
    }

    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);

        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle bundle,
                              String s,
                              ContentProviderClient contentProviderClient,
                              SyncResult syncResult) {

        Log.d(LOG_TAG, "Attempting sync...");

        List<Schedule> schedules = syncComperio(ScheduleTable.CONTENT_URI);

        if(!schedules.isEmpty()) {
            UserData userData = getPersistanceManager().loadUserData();
            userData.suggestedSchedule = schedules.get(0);
            getPersistanceManager().saveUserData(userData);
        }


    }

    private List<Schedule> syncComperio(Uri uri) {
        UserData userData = getPersistanceManager().loadUserData();

        ComperioService comperioService = ComperioApplication.get(context).getComperioService();
        List<Schedule> schedules = null;
        Log.d(LOG_TAG,"UserData: "+ userData.toString());
        try {
            schedules = comperioService.listSchedules(
                    userData.subject,
                    userData.maxDistance,
                    userData.userLoc[1],
                    userData.userLoc[0])
                    .execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (schedules == null || schedules.isEmpty()) {
            Log.e(LOG_TAG, "No data returned. schedules == null || schedules.isEmpty().");
            return new ArrayList<>();
        }

        Log.e(LOG_TAG, "Fetched " + schedules.size() + " schedules.");

        Vector<ContentValues> cVVector = getContentVVectorFor(schedules);

        if (cVVector.size() > 0) {
            getContext().getContentResolver().delete(uri, null, null);

            ContentValues[] cvArray = new ContentValues[cVVector.size()];
            cVVector.toArray(cvArray);

            getContext().getContentResolver().bulkInsert(uri, cvArray);
        }

        Log.d(LOG_TAG, "Synced " + uri.getLastPathSegment() + ". " + cVVector.size() + " added");

        return schedules;
    }

    private Vector<ContentValues> getContentVVectorFor(List<Schedule> schedules) {
        Vector<ContentValues> contentValuesVector = new Vector<>(schedules.size());

        for (Schedule schedule : schedules) {
            contentValuesVector.add(getContentValuesFrom(schedule));
        }

        return contentValuesVector;
    }

    public static ContentValues getContentValuesFrom(Schedule schedule){
        ContentValues values = new ContentValues();

        values.put(ScheduleTable.COLUMN_SCHEDULE_ID, schedule._id);
        values.put(ScheduleTable.COLUMN_SUBJECT_NAME, schedule.subjectName);
        values.put(ScheduleTable.COLUMN_TEACHER_NAME, schedule.teacherName);
        values.put(ScheduleTable.COLUMN_TEACHER_STORY, schedule.teacherStory);
        values.put(ScheduleTable.COLUMN_TEACHER_PIC_URL, schedule.teacherPicUrl);
        values.put(ScheduleTable.COLUMN_TEACHER_LAT, schedule.loc[1]);
        values.put(ScheduleTable.COLUMN_TEACHER_LONG, schedule.loc[0]);
        values.put(ScheduleTable.COLUMN_TEACHER_PHONE, schedule.teacherPhone);
        values.put(ScheduleTable.COLUMN_TEACHER_RATING, schedule.teacherRating);
        values.put(ScheduleTable.COLUMN_HOUR_PRICE, schedule.hourPrice);
        values.put(ScheduleTable.COLUMN_TEACHER_DISTANCE,schedule.distance);

        return values;
    }


    private PersistenceManager getPersistanceManager(){
        return new PersistenceManager(context);
    }

}
