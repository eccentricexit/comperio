package com.rigel.comperio.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.rigel.comperio.R;

public abstract class BottomNavigationActivity extends BaseActivity {

    private static final String DEFAULT_FRAGMENT = HomeFragment.TAG;
    private static final String CURRENT_FRAGMENT_KEY = "current.fragment.key";
    private static final int FRAME_LAYOUT_ID = R.id.frame_layout;

    private String currentFragment = DEFAULT_FRAGMENT;

    private FavoritesFragment favoritesFragment;
    private FiltersFragment filtersFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBottomNavigationView();
        if(savedInstanceState==null) {
            display(DEFAULT_FRAGMENT);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentFragment = savedInstanceState.getString(CURRENT_FRAGMENT_KEY);

        display(currentFragment);
    }

    private void display(String fragmentTag) {
        currentFragment = fragmentTag;

        switch (fragmentTag){
            case (HomeFragment.TAG):{
                if(homeFragment==null){
                    homeFragment = HomeFragment.newInstance();
                }
                transact(homeFragment);
                break;
            }
            case (FavoritesFragment.TAG):{
                if(favoritesFragment==null){
                    favoritesFragment = FavoritesFragment.newInstance();
                }
                transact(favoritesFragment);
                break;
            }
            case (FiltersFragment.TAG):{
                if(filtersFragment==null){
                    filtersFragment = FiltersFragment.newInstance();
                }
                transact(filtersFragment);
                break;
            }
        }
    }

    private void transact(BaseFragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(FRAME_LAYOUT_ID, fragment);
        transaction.commit();
    }

    @NonNull
    private void setupBottomNavigationView() {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home: {
                                display(HomeFragment.TAG);
                                break;
                            }
                            case R.id.navigation_favorites: {
                                display(FavoritesFragment.TAG);
                                break;
                            }
                            case R.id.navigation_filters: {
                                display(FiltersFragment.TAG);
                                break;
                            }
                        }
                        return true;
                    }

                }
        );

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(CURRENT_FRAGMENT_KEY,currentFragment);

        FragmentManager manager = getFragmentManager();

        if(homeFragment!=null && HomeFragment.TAG.equals(currentFragment)) {
            manager.putFragment(outState, HomeFragment.TAG, homeFragment);
        }

        if(favoritesFragment!=null && FavoritesFragment.TAG.equals(currentFragment)) {
            manager.putFragment(outState, FavoritesFragment.TAG, favoritesFragment);
        }

        if(filtersFragment!=null && FiltersFragment.TAG.equals(currentFragment)) {
            manager.putFragment(outState, FiltersFragment.TAG, filtersFragment);
        }
        super.onSaveInstanceState(outState);
    }

}
