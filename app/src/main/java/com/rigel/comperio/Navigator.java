package com.rigel.comperio;

import com.rigel.comperio.model.Schedule;

public interface Navigator {

    void navigateToHomeActivity();

    void navigateToDetailsActivity(Schedule schedule);

    void navigateToAddContact(Schedule schedule);

    void navigateToNewSchedule();
}
