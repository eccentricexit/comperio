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
import com.rigel.comperio.R;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.data.ComperioContract.ScheduleEntry;
import com.rigel.comperio.model.Schedule;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String LOG_TAG = SyncAdapter.class.getSimpleName();

    private Context context;

    public SyncAdapter(Context context) {
        super(context, true);
        this.context = context;
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
        syncImmediately(context);
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

    private static void syncImmediately(Context context) {
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

        syncComperio(ScheduleEntry.CONTENT_URI);
    }

    private void syncComperio(Uri uri) {

        ComperioService comperioService = ComperioApplication.get(context).getComperioService();
        List<Schedule> schedules = null;
        try {
            schedules = comperioService.listSchedules().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (schedules == null || schedules.isEmpty()) {
            Log.e(LOG_TAG, "No data returned. schedules == null || schedules.isEmpty().");
            return;
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
    }

    private Vector<ContentValues> getContentVVectorFor(List<Schedule> schedules) {
        Vector<ContentValues> contentValuesVector = new Vector<>(schedules.size());

        for (Schedule schedule : schedules) {
            ContentValues values = new ContentValues();

            values.put(ScheduleEntry.COLUMN_SUBJECT_NAME, schedule.subjectName);
            values.put(ScheduleEntry.COLUMN_TEACHER_NAME, schedule.teacherName);
            values.put(ScheduleEntry.COLUMN_TEACHER_STORY, schedule.teacherStory);
            values.put(ScheduleEntry.COLUMN_TEACHER_PIC_URL, schedule.teacherPicUrl);
            values.put(ScheduleEntry.COLUMN_TEACHER_LAT, schedule.loc[1]);
            values.put(ScheduleEntry.COLUMN_TEACHER_LONG, schedule.loc[0]);
            values.put(ScheduleEntry.COLUMN_TEACHER_PHONE, schedule.teacherPhone);
            values.put(ScheduleEntry.COLUMN_TEACHER_RATING, schedule.teacherRating);
            values.put(ScheduleEntry.COLUMN_WEEK_DAYS, schedule.weekDaysAvailable);
            values.put(ScheduleEntry.COLUMN_HOUR_PRICE, schedule.hourPrice);
            values.put(ScheduleEntry.COLUMN_START_HOUR, schedule.startHour);
            values.put(ScheduleEntry.COLUMN_START_MINUTE, schedule.startMinute);
            values.put(ScheduleEntry.COLUMN_END_HOUR, schedule.endHour);
            values.put(ScheduleEntry.COLUMN_END_MINUTE, schedule.endMinute);

            contentValuesVector.add(values);
        }
        return contentValuesVector;
    }

}
