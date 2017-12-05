package com.rigel.comperio.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.model.Schedule;
import com.squareup.picasso.Picasso;


public class ComperioWidgetProvider extends AppWidgetProvider {

    private static final String LOG_TAG = ComperioWidgetProvider.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Schedule schedule = new PersistenceManager(context).loadUserData().suggestedSchedule;

        if(schedule==null){
            schedule = new Schedule();
            schedule.subjectName = context.getString(R.string.msg_no_data_available);
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.comperio_widget);
        views.setTextViewText(R.id.lblSubjectWidget,schedule.subjectName );
        views.setTextViewText(R.id.lblTeacherNameWidget,schedule.teacherName );
        Picasso.with(context)
                .load(schedule.teacherPicUrl)
                .into(views, R.id.imgProfilePicWidget,appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

