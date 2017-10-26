package com.rigel.comperio;

import com.rigel.comperio.model.Schedule;

public interface Navigator {
    void navigateToFreeTimeActivity();
    void navigateToHomeActivity();
    void navigateToDetailsActivity(Schedule schedule);
}
