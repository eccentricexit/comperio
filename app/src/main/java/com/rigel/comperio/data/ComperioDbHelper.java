package com.rigel.comperio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rigel.comperio.data.ComperioContract.FavoriteEntry;
import com.rigel.comperio.data.ComperioContract.ScheduleEntry;

public class ComperioDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "comperio.db";
    private static final int DATABASE_VERSION = 1;

    public ComperioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        final String SQL_CREATE_SCHEDULE_TABLE = "CREATE TABLE " + ScheduleEntry.TABLE_NAME + " (" +
                ScheduleEntry._ID + " INTEGER PRIMARY KEY," +

                ScheduleEntry.COLUMN_HOUR_PRICE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_START_HOUR + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_START_MINUTE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_END_HOUR + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_END_MINUTE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_WEEK_DAYS + " TEXT NOT NULL, " +
                ScheduleEntry.COLUMN_SUBJECT_NAME + " TEXT NOT NULL, " +

                ScheduleEntry.COLUMN_TEACHER_NAME + " TEXT NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_PHONE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_STORY + " TEXT NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_LAT + " REAL NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_LONG + " REAL NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_RATING + " REAL NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_PIC_URL + " TEXT NOT NULL, " +


                " );";

        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                FavoriteEntry.COLUMN_SCHEDULE_KEY + " INTEGER NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_SCHEDULE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ScheduleEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
