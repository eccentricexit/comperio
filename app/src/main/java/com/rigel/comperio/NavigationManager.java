package com.rigel.comperio;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.view.MainActivity;
import com.rigel.comperio.view.NewScheduleActivity;
import com.rigel.comperio.view.ScheduleDetailActivity;

public class NavigationManager {

    public static final int REQUEST_CODE_ADD_TO_CONTACTS = 0;

    private Context context;
    private View transitionView;

    public NavigationManager(Context context){
        this.context = context;
    }

    public void navigateToHomeActivity() {
        navigate(MainActivity.class);
    }

    public void navigateToScheduleDetails(Schedule schedule) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRA_SCHEDULE), schedule);
        context.startActivity(intent);
    }

    public void navigateToScheduleDetails(Schedule schedule, ActivityOptionsCompat options) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRA_SCHEDULE), schedule);
        context.startActivity(intent,options.toBundle());
    }

    public void navigateToAddContact(Schedule schedule, AppCompatActivity callingActivity) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, schedule.teacherName);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, schedule.teacherPhone);
        intent.putExtra(ContactsContract.Intents.Insert.COMPANY, context.getString(R.string.app_name));
        intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE,
                schedule.subjectName+" "+context.getString(R.string.teacher));

       callingActivity.startActivityForResult(intent, REQUEST_CODE_ADD_TO_CONTACTS);
    }

    public void navigateToNewSchedule() {
        navigate(NewScheduleActivity.class);
    }

    private void navigate(Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }
}
