package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewScheduleViewModel extends BaseViewModel {

    public Subject[] subjects;

    public Schedule schedule;
    public NewScheduleViewModel(Navigator navigator, PersistenceManager persistenceManager,
                         DevUtils.Logger logger) {
        super(navigator, persistenceManager, logger);

        schedule = new Schedule();
        subjects = DevUtils.getFakeSubjects();
    }

    public void onClickLocation(View view){
        logger.toast("onClickLocation");
    }

    public void onClickPublish(View view){
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
        schedule.hourPrice = Float.parseFloat(hourPrice);
    }
}
