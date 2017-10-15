package com.rigel.comperio.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rigel.comperio.data.ComperioContract.*;
import com.rigel.comperio.viewmodel.FavoritesViewModel;

public class ComperioDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "comperio.db";
    private static final int DATABASE_VERSION = 1;

    public ComperioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_TEACHER_TABLE = "CREATE TABLE " + TeacherEntry.TABLE_NAME + " (" +
                TeacherEntry._ID + " INTEGER PRIMARY KEY," +
                TeacherEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TeacherEntry.COLUMN_PHONE + " INTEGER NOT NULL, " +
                TeacherEntry.COLUMN_RATING + " REAL NOT NULL, " +
                TeacherEntry.COLUMN_STORY + " TEXT NOT NULL, " +
                TeacherEntry.COLUMN_COORD_LAT + " REAL NOT NULL, " +
                TeacherEntry.COLUMN_COORD_LONG + " REAL NOT NULL " +
                " );";

        final String SQL_CREATE_SUBJECT_TABLE = "CREATE TABLE " + SubjectEntry.TABLE_NAME + " (" +
                SubjectEntry._ID + " INTEGER PRIMARY KEY," +
                SubjectEntry.COLUMN_NAME + " TEXT NOT NULL " +
                " );";

        final String SQL_CREATE_SCHEDULE_TABLE = "CREATE TABLE " + ScheduleEntry.TABLE_NAME + " (" +
                ScheduleEntry._ID + " INTEGER PRIMARY KEY," +
                ScheduleEntry.COLUMN_SUBJECT_KEY + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_TEACHER_KEY + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_HOUR_PRICE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUM_START_HOUR + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUM_START_MINUTE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUM_END_HOUR + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUM_END_MINUTE + " INTEGER NOT NULL, " +
                ScheduleEntry.COLUMN_WEEK_DAYS + " TEXT NOT NULL " +
                " );";

        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
                FavoriteEntry._ID + " INTEGER PRIMARY KEY," +
                FavoriteEntry.COLUMN_SCHEDULE_KEY + " INTEGER NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_SUBJECT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TEACHER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_SCHEDULE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ScheduleEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SubjectEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TeacherEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
