package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.rigel.comperio.R;

public abstract class BottomNavigationActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getLogger().toast("onNavigationItemSelected called");
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getNavigator().navigateToHomeActivity();
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

        };

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
