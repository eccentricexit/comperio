package com.rigel.comperio.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.rigel.comperio.data.ComperioContract.FavoriteTable;

public class ComperioProvider extends ContentProvider {

    private static final int SCHEDULE = 100;
    private static final int FAVORITE = 200;

    private static final UriMatcher uriMatcher = buildUriMatcher();

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

        final int match = uriMatcher.match(uri);

        switch (match) {
            case SCHEDULE:
                return ComperioContract.ScheduleTable.CONTENT_TYPE;
            case FAVORITE:
                return FavoriteTable.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor retCursor;

        switch (uriMatcher.match(uri)) {
            case SCHEDULE: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.ScheduleTable.TABLE_NAME,
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
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.FavoriteTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
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
        final int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case SCHEDULE: {
                long _id = db.insert(ComperioContract.ScheduleTable.TABLE_NAME,
                        null, values);
                if (_id > 0)
                    returnUri = ComperioContract.ScheduleTable.buildScheduleUriWith(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FAVORITE: {
                long _id = db.insert(FavoriteTable.TABLE_NAME,
                        null, values);
                if (_id > 0)
                    returnUri = FavoriteTable.buildFavoriteUriWith(_id);
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
        final int match = uriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case SCHEDULE:
                rowsDeleted = db.delete(
                        ComperioContract.ScheduleTable.TABLE_NAME, selection, selectionArgs);
                break;
            case FAVORITE:
                rowsDeleted = db.delete(
                        FavoriteTable.TABLE_NAME, selection, selectionArgs);
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
        final int match = uriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case SCHEDULE:
                rowsUpdated = db.update(ComperioContract.ScheduleTable.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case FAVORITE:
                rowsUpdated = db.update(FavoriteTable.TABLE_NAME, values, selection,
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
        final int match = uriMatcher.match(uri);
        switch (match) {

            case SCHEDULE: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.ScheduleTable.TABLE_NAME,
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
                        long _id = db.insert(FavoriteTable.TABLE_NAME,
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
