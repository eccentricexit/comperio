package com.rigel.comperio.view;

import android.app.Fragment;
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
    private static final String CURRENT_FRAGMENT_KEY = "current_fragment_key";

    private String currentFragment;
    private int currentView;

    private FavoritesFragment favoritesFragment;
    private FiltersFragment filtersFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLogger().toast("onCreate");

        setupBottomNavigationView();

        if(savedInstanceState==null){
            currentView = R.id.frame_layout;
            display(DEFAULT_FRAGMENT);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getLogger().toast("onRestoreInstanceState");

        FragmentManager manager = getFragmentManager();
        homeFragment = (HomeFragment) manager.getFragment(savedInstanceState,HomeFragment.TAG);
        favoritesFragment = (FavoritesFragment) manager.getFragment(savedInstanceState,FavoritesFragment.TAG);
        filtersFragment = (FiltersFragment) manager.getFragment(savedInstanceState,FiltersFragment.TAG);


        currentFragment = savedInstanceState.getString(CURRENT_FRAGMENT_KEY);
        display(currentFragment);
    }

    private void display(String fragmentTag) {
        getLogger().toast("display:"+fragmentTag);
        switch (fragmentTag){
            case (HomeFragment.TAG):{
                if(homeFragment==null){
                    homeFragment = HomeFragment.newInstance();
                }
                currentFragment = HomeFragment.TAG;
                transact(homeFragment);
                break;
            }
            case (FavoritesFragment.TAG):{
                if(favoritesFragment==null){
                    favoritesFragment = FavoritesFragment.newInstance();
                }
                currentFragment = FavoritesFragment.TAG;
                transact(favoritesFragment);
                break;
            }
            case (FiltersFragment.TAG):{
                if(filtersFragment==null){
                    filtersFragment = FiltersFragment.newInstance();
                }
                currentFragment = FiltersFragment.TAG;
                transact(filtersFragment);
                break;
            }
        }
    }

    private void transact(BaseFragment fragment) {
        getLogger().toast("transact: "+fragment.getFragmentTag());
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        getLogger().log("transact + view before "+currentView);
        transaction.replace(currentView, fragment);
        transaction.commit();

        currentView = fragment.getId();
        getLogger().log("transact + view after "+currentView);
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
                                display(HomeFragment.TAG);
                                break;
                            case R.id.navigation_favorites:
                                display(FavoritesFragment.TAG);
                                break;
                            case R.id.navigation_filters:
                                display(FiltersFragment.TAG);
                                break;
                        }
                        return true;
                    }

                }
        );

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getLogger().toast("onSaveInstanceState");

        outState.putString(CURRENT_FRAGMENT_KEY,currentFragment);

        FragmentManager manager = getFragmentManager();
        if(homeFragment!=null) {
            manager.putFragment(outState, HomeFragment.TAG, homeFragment);
        }

        if(favoritesFragment!=null) {
            manager.putFragment(outState, FavoritesFragment.TAG, favoritesFragment);
        }

        if(filtersFragment!=null) {
            manager.putFragment(outState, FiltersFragment.TAG, filtersFragment);
        }

        super.onSaveInstanceState(outState);
    }



}
