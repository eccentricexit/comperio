package com.rigel.comperio.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ComperioProvider extends ContentProvider {

    static final int SUBJECT = 100;
    static final int TEACHER = 200;
    static final int SCHEDULE = 300;
    static final int FAVORITE = 400;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final SQLiteQueryBuilder sScheduleByFavoriteQueryBuilder;
    private static final SQLiteQueryBuilder sTeacherByScheduleQueryBuilder;
    private static final SQLiteQueryBuilder sSubjectByScheduleQueryBuilder;

    static {
        sScheduleByFavoriteQueryBuilder = new SQLiteQueryBuilder();
        sScheduleByFavoriteQueryBuilder.setTables(
                ComperioContract.FavoriteEntry.TABLE_NAME + " INNER JOIN " +
                        ComperioContract.ScheduleEntry.TABLE_NAME +
                        " ON " + ComperioContract.FavoriteEntry.TABLE_NAME +
                        "." + ComperioContract.FavoriteEntry.COLUMN_SCHEDULE_KEY +
                        " = " + ComperioContract.ScheduleEntry.TABLE_NAME +
                        "." + ComperioContract.ScheduleEntry._ID);

        sTeacherByScheduleQueryBuilder = new SQLiteQueryBuilder();
        sTeacherByScheduleQueryBuilder.setTables(
                ComperioContract.ScheduleEntry.TABLE_NAME + " INNER JOIN " +
                        ComperioContract.TeacherEntry.TABLE_NAME +
                        " ON " + ComperioContract.ScheduleEntry.TABLE_NAME +
                        "." + ComperioContract.ScheduleEntry.COLUMN_TEACHER_KEY +
                        " = " + ComperioContract.TeacherEntry.TABLE_NAME +
                        "." + ComperioContract.TeacherEntry._ID);

        sSubjectByScheduleQueryBuilder = new SQLiteQueryBuilder();
        sSubjectByScheduleQueryBuilder.setTables(
                ComperioContract.ScheduleEntry.TABLE_NAME + " INNER JOIN " +
                        ComperioContract.SubjectEntry.TABLE_NAME +
                        " ON " + ComperioContract.ScheduleEntry.TABLE_NAME +
                        "." + ComperioContract.ScheduleEntry.COLUMN_SUBJECT_KEY +
                        " = " + ComperioContract.SubjectEntry.TABLE_NAME +
                        "." + ComperioContract.SubjectEntry._ID);
    }

    private ComperioDbHelper mOpenHelper;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ComperioContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, ComperioContract.PATH_SUBJECT, SUBJECT);
        matcher.addURI(authority, ComperioContract.PATH_TEACHER, TEACHER);
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
            case SUBJECT:
                return ComperioContract.SubjectEntry.CONTENT_ITEM_TYPE;
            case TEACHER:
                return ComperioContract.TeacherEntry.CONTENT_ITEM_TYPE;
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
            case SUBJECT: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.SubjectEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case TEACHER: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.TeacherEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
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
                retCursor = mOpenHelper.getReadableDatabase().query(
                        ComperioContract.FavoriteEntry.TABLE_NAME,
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
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case TEACHER: {
                long _id = db.insert(ComperioContract.TeacherEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ComperioContract.TeacherEntry.buildTeacherUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case SUBJECT: {
                long _id = db.insert(ComperioContract.SubjectEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ComperioContract.SubjectEntry.buildSubjectUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case SCHEDULE: {
                long _id = db.insert(ComperioContract.ScheduleEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ComperioContract.ScheduleEntry.buildScheduleUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case FAVORITE: {
                long _id = db.insert(ComperioContract.FavoriteEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = ComperioContract.FavoriteEntry.buildFavoriteUri(_id);
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
            case SUBJECT:
                rowsDeleted = db.delete(
                        ComperioContract.SubjectEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case TEACHER:
                rowsDeleted = db.delete(
                        ComperioContract.TeacherEntry.TABLE_NAME, selection, selectionArgs);
                break;
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
            case SUBJECT:
                rowsUpdated = db.update(ComperioContract.SubjectEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case TEACHER:
                rowsUpdated = db.update(ComperioContract.TeacherEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
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
            case TEACHER: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.TeacherEntry.TABLE_NAME, null, value);
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

            case SUBJECT: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.SubjectEntry.TABLE_NAME, null, value);
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

            case SCHEDULE: {
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(ComperioContract.ScheduleEntry.TABLE_NAME, null, value);
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
                        long _id = db.insert(ComperioContract.FavoriteEntry.TABLE_NAME, null, value);
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

            default:
                return super.bulkInsert(uri, values);
        }
    }

}
