package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.LoggingManager;
import com.rigel.comperio.NavigationManager;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScheduleViewModel extends BaseViewModel {

    public Subject[] subjects;
    public Schedule schedule;

    public NewScheduleViewModel(NavigationManager navigator, PersistenceManager persistenceManager,
                                LoggingManager logger) {
        super(navigator, persistenceManager, logger);

        schedule = new Schedule();
        subjects = DevUtils.getFakeSubjects();
    }

    public void onClickLocation(View view){
        logger.toast("onClickLocation");
    }

    public void onClickPublish(View view){
        schedule.loc = persistenceManager.loadUserData().userLoc;

        persistenceManager
                .publishNewSchedule(schedule)
                .enqueue(new Callback<Schedule>() {
                    @Override
                    public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                        logger.toast("Got response");
                    }

                    @Override
                    public void onFailure(Call<Schedule> call, Throwable t) {
                        logger.toast("Failure");
                    }
                });
    }

    public String getHourPrice(){
        return schedule.hourPrice.toString();
    }

    public void setHourPrice(String hourPrice){
        if("".equals(hourPrice)){hourPrice="0";}
        schedule.hourPrice = Float.parseFloat(hourPrice);
    }
}