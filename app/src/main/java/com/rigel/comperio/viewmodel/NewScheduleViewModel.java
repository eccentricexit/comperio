package com.rigel.comperio.viewmodel;

import android.view.View;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;

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
        logger.toast("onClickPublish");
    }

    public String getHourPrice(){
        return schedule.hourPrice.toString();
    }

    public void setHourPrice(String hourPrice){
        schedule.hourPrice = Float.parseFloat(hourPrice);
    }
}
