package com.rigel.comperio.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.rigel.comperio.R;

public abstract class BottomNavigationActivity extends BaseActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

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
