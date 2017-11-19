package com.rigel.comperio.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.rigel.comperio.data.ComperioContract.FavoriteEntry;
import com.rigel.comperio.data.ComperioContract.ScheduleEntry;

public class ComperioProvider extends ContentProvider {

    private static final int SCHEDULE = 100;
    private static final int FAVORITE = 200;
    private static final int FAVORITE_BY_ID = 201;

    private static final UriMatcher sUriMatcher = buildUriMatcher();



    private ComperioDbHelper mOpenHelper;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ComperioContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, ComperioContract.PATH_SCHEDULE, SCHEDULE);
        matcher.addURI(authority, ComperioContract.PATH_FAVORITE, FAVORITE);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new ComperioDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case SCHEDULE:
                return ComperioContract.ScheduleEntry.CONTENT_TYPE;
            case FAVORITE:
                return ComperioContract.FavoriteEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case SCHEDULE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.ScheduleEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case FAVORITE: {
                String rawQuery = "SELECT * FROM " +
                        ScheduleEntry.TABLE_NAME + " INNER JOIN " +
                        FavoriteEntry.TABLE_NAME + " ON " +
                        ScheduleEntry.TABLE_NAME + "." + ScheduleEntry.COLUMN_SCHEDULE_ID + " = " +
                        FavoriteEntry.TABLE_NAME + "." + FavoriteEntry.COLUMN_SCHEDULE_KEY;

                retCursor = mOpenHelper.getReadableDatabase().rawQuery(rawQuery, null);
                break;
            }

            case FAVORITE_BY_ID:{
                String rawQuery = "SELECT * FROM " +
                        ScheduleEntry.TABLE_NAME + " INNER JOIN " +
                        FavoriteEntry.TABLE_NAME + " ON " +
                        ScheduleEntry.TABLE_NAME + "." + ScheduleEntry.COLUMN_SCHEDULE_ID + " = " +
                        FavoriteEntry.TABLE_NAME + "." + FavoriteEntry.COLUMN_SCHEDULE_KEY +
                        " WHERE "+FavoriteEntry.COLUMN_SCHEDULE_KEY+" = ?";

                retCursor = mOpenHelper.getReadableDatabase().rawQuery(rawQuery, selectionArgs);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case SCHEDULE: {
                long _id = db.insert(ComperioContract.ScheduleEntry.TABLE_NAME,
                        null, values);
                if (_id > 0)
                    returnUri = ComperioContract.ScheduleEntry.buildScheduleUriWith(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FAVORITE: {
                long _id = db.insert(ComperioContract.FavoriteEntry.TABLE_NAME,
                        null, values);
                if (_id > 0)
                    returnUri = ComperioContract.FavoriteEntry.buildFavoriteUriWith(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case SCHEDULE:
                rowsDeleted = db.delete(
                        ComperioContract.ScheduleEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVORITE:
                rowsDeleted = db.delete(
                        ComperioContract.FavoriteEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(
            Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case SCHEDULE:
                rowsUpdated = db.update(ComperioContract.ScheduleEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case FAVORITE:
                rowsUpdated = db.update(ComperioContract.FavoriteEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {

            case SCHEDULE: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.ScheduleEntry.TABLE_NAME,
                                null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            }

            case FAVORITE: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.FavoriteEntry.TABLE_NAME,
                                null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            }

            default: {
                getContext().getContentResolver().notifyChange(uri, null);
                return super.bulkInsert(uri, values);
            }
        }
    }

}
