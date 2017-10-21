package com.rigel.comperio.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

public abstract class BaseActivity extends AppCompatActivity  {

    private Navigator navigator;

    protected Navigator getNavigator() {
        if(navigator != null){
            return navigator;
        }

        navigator = new Navigator() {
            @Override
            public void navigateToFreeTimeActivity() {
                navigate(FreeTimeActivity.class);
            }

            @Override
            public void navigateToMainActivity() {
                navigate(HomeActivity.class);
            }

            @Override
            public void navigateToFavoritesActivity() {
                navigate(FavoritesActivity.class);
            }

            @Override
            public void navigateToFiltersActivity() {
                navigate(FiltersActivity.class);
            }

            @Override
            public void navigateToDetailsActivity(Schedule schedule) {
                ScheduleDetailActivity.launch(getContext(),schedule);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };

        return navigator;
    }

    protected abstract Context getContext();
}
