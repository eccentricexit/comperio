package com.rigel.comperio;

import com.rigel.comperio.model.Schedule;

public interface Navigator {
    void navigateToFreeTimeActivity();
    void navigateToMainActivity();
    void navigateToFavoritesActivity();
    void navigateToFiltersActivity();
    void navigateToDetailsActivity(Schedule schedule);
}
