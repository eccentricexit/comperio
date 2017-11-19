package com.rigel.comperio.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ComperioContract {

    public static final String CONTENT_AUTHORITY = "com.rigel.comperio";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String COMPERIO_BASE_URL = "http://localhost:3000/v1/";

    public static final String PATH_SCHEDULE = "schedules";
    public static final String PATH_FAVORITE = "favorites";


    private static Uri.Builder getScheduleUriBuilder() {
        return Uri.parse(COMPERIO_BASE_URL).buildUpon().appendPath(PATH_SCHEDULE);
    }


    public static final class ScheduleEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SCHEDULE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCHEDULE;


        public static final String TABLE_NAME = "schedules";

        public static final String COLUMN_SCHEDULE_ID = "schedule_id";
        public static final String COLUMN_HOUR_PRICE = "hour_price";
        public static final String COLUMN_START_HOUR = "start_hour";
        public static final String COLUMN_START_MINUTE = "start_minute";
        public static final String COLUMN_END_HOUR = "end_hour";
        public static final String COLUMN_END_MINUTE = "end_minute";
        public static final String COLUMN_WEEK_DAYS = "week_days";
        public static final String COLUMN_SUBJECT_NAME = "subject_name";
        public static final String COLUMN_TEACHER_NAME = "teacher_name";
        public static final String COLUMN_TEACHER_RATING = "teacher_rating";
        public static final String COLUMN_TEACHER_PHONE = "teacher_phone";
        public static final String COLUMN_TEACHER_STORY = "teacher_story";
        public static final String COLUMN_TEACHER_LAT = "teacher_coord_lat";
        public static final String COLUMN_TEACHER_LONG = "teacher_coord_long";
        public static final String COLUMN_TEACHER_PIC_URL = "teacher_pic_url";
        public static final String COLUMN_TEACHER_DISTANCE = "distance";


        public static Uri buildScheduleUriWith(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAVORITE;


        public static final String TABLE_NAME = "favorites";

        public static final String COLUMN_SCHEDULE_KEY = "schedule_id";


        public static Uri buildFavoriteUriWith(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
