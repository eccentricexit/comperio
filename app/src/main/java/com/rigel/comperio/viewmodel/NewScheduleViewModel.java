package com.rigel.comperio.viewmodel;

import android.text.TextUtils;
import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.R;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.view.NewScheduleActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScheduleViewModel extends BaseViewModel {

    private final NewScheduleActivity newScheduleActivity;
    public Subject[] subjects;
    public Schedule schedule;
    public Boolean isConnectedToInternet;

    public NewScheduleViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                                LoggingManager logger, NewScheduleActivity newScheduleActivity) {
        super(navigator, persistenceManager, logger);

        this.schedule = new Schedule();
        this.subjects = DevUtils.getFakeSubjects();
        this.newScheduleActivity = newScheduleActivity;
    }

    public void onClickPublish(View view){
        if(!isConnectedToInternet){
            logger.toast(newScheduleActivity.getResources().getString(R.string.msg_no_internet));
            return;
        }

        schedule.loc = persistenceManager.loadUserData().userLoc;

        persistenceManager
                .publishNewSchedule(schedule)
                .enqueue(new Callback<Schedule>() {
                    @Override
                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                        logger.toast(
                                newScheduleActivity
                                        .getResources()
                                        .getString(R.string.msg_schedule_published)
                        );
                    }

                    @Override
                    public void onFailure(Call<Schedule> call, Throwable t) {
                        logger.toast(
                                newScheduleActivity
                                        .getResources()
                                        .getString(R.string.msg_error_publishing_schedule)
                        );
                        logger.log(t.getMessage());
                    }
                }
        );

        newScheduleActivity.finish();
    }

    public String getHourPrice(){
        return schedule.hourPrice.toString();
    }

    public void setHourPrice(String hourPrice){
        if(TextUtils.isEmpty(hourPrice)){hourPrice="0";}
        schedule.hourPrice = Float.parseFloat(hourPrice);
    }
}
