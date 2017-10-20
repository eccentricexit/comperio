package com.rigel.comperio.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.model.Schedule;

import java.util.Observer;

public abstract class BaseActivity extends AppCompatActivity
        implements Observer,
        BottomNavigationView.OnNavigationItemSelectedListener {

    Navigator navigator;

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
                navigate(ScheduleDetailActivity.class);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };

        return navigator;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getNavigator().navigateToMainActivity();
                return true;
            case R.id.navigation_favorites:
                getNavigator().navigateToFavoritesActivity();
                return true;
            case R.id.navigation_filters:
                getNavigator().navigateToFiltersActivity();
                return true;
        }
        throw new UnsupportedOperationException();
    }
}
