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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rigel.comperio.R;
import com.rigel.comperio.data.ComperioContract;
import com.rigel.comperio.data.ComperioContract.*;
import com.rigel.comperio.model.Schedule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Vector;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String LOG_TAG = SyncAdapter.class.getSimpleName();


    public SyncAdapter(Context context) {
        super(context, true);
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

        URL schedulesUrl = ComperioContract.buildUrlFor(ComperioContract.PATH_SCHEDULE);


        String schedulesJson = getJsonResponseFor(schedulesUrl);

        syncComperio(schedulesJson, ScheduleEntry.CONTENT_URI);

    }

    private void syncComperio(String json, Uri uri) {
        if (json == null) {
            Log.e(LOG_TAG, "Error: Could not sync, topRatedJson == null");
            return;
        }
        List<Schedule> schedules = getMoviesFromJSON(json);
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

            values.put(ScheduleEntry.COLUMN_SUBJECT_NAME, schedule.getSubjectName());
            values.put(ScheduleEntry.COLUMN_TEACHER_NAME, schedule.getTeacherName());
            values.put(ScheduleEntry.COLUMN_TEACHER_STORY, schedule.getTeacherStory());
            values.put(ScheduleEntry.COLUMN_TEACHER_PIC_URL, schedule.getTeacherPicUrl());
            values.put(ScheduleEntry.COLUMN_TEACHER_LAT, schedule.getLoc()[1]);
            values.put(ScheduleEntry.COLUMN_TEACHER_LONG, schedule.getLoc()[0]);
            values.put(ScheduleEntry.COLUMN_TEACHER_PHONE, schedule.getTeacherPhone());
            values.put(ScheduleEntry.COLUMN_TEACHER_RATING, schedule.getTeacherRating());
            values.put(ScheduleEntry.COLUMN_WEEK_DAYS, schedule.getWeekDaysAvailable());
            values.put(ScheduleEntry.COLUMN_HOUR_PRICE, schedule.getHourPrice());
            values.put(ScheduleEntry.COLUMN_START_HOUR, schedule.getStartHour());
            values.put(ScheduleEntry.COLUMN_START_MINUTE, schedule.getStartMinute());
            values.put(ScheduleEntry.COLUMN_END_HOUR, schedule.getEndHour());
            values.put(ScheduleEntry.COLUMN_END_MINUTE, schedule.getEndMinute());

            contentValuesVector.add(values);
        }
        return contentValuesVector;
    }

    private List<Schedule> getMoviesFromJSON(String json) {

        Type listType = new TypeToken<List<Schedule>>() {
        }.getType();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            return new Gson().fromJson(jsonObject.get("results").toString(), listType);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getJsonResponseFor(URL url) {

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream == null)
                return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line; //for debugging purposes
            while ((line = reader.readLine()) != null)
                buffer.append(line).append("\n");


            if (buffer.length() == 0)
                return null;

            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
