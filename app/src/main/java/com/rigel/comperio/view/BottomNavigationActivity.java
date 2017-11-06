package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.rigel.comperio.R;

public abstract class BottomNavigationActivity extends BaseActivity {

    private static final String CURRENT_SCREEN_KEY = "current_screen_key";
    private static final int HOME_SCREEN = 0;
    private static final int FAVORITES_SCREEN = 1;
    private static final int FILTERS_SCREEN = 2;

    private int currentScreen = HOME_SCREEN; // Default screen

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentScreen = savedInstanceState.getInt(CURRENT_SCREEN_KEY);
        }

        setupBottomNavigationView();

        showFragment(selectFragmentFor(currentScreen));
    }

    @NonNull
    private void setupBottomNavigationView() {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                currentScreen = HOME_SCREEN;
                                break;
                            case R.id.navigation_favorites:
                                currentScreen = FAVORITES_SCREEN;
                                break;
                            case R.id.navigation_filters:
                                currentScreen = FILTERS_SCREEN;
                                break;
                        }

                        showFragment(selectFragmentFor(currentScreen));
                        return true;
                    }

                });

        navigation.getMenu().getItem(currentScreen).setChecked(true);
    }

    private Fragment selectFragmentFor(int currentScreen) {
        switch (currentScreen) {
            case HOME_SCREEN: {
                return HomeFragment.newInstance();
            }
            case FAVORITES_SCREEN: {
                return FavoritesFragment.newInstance();
            }
            case FILTERS_SCREEN: {
                return FiltersFragment.newInstance();
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_SCREEN_KEY, currentScreen);
        super.onSaveInstanceState(outState);
    }

}
