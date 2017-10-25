package com.rigel.comperio.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.ActivityFavoritesBinding;
import com.rigel.comperio.viewmodel.FavoritesViewModel;

import java.util.Observable;
import java.util.Observer;

public class FavoritesActivity extends BottomNavigationActivity implements Observer {

    private ActivityFavoritesBinding favoritesActivityBinding;
    private FavoritesViewModel favoritesViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupListScheduleView(favoritesActivityBinding.recyclerFavorites);
        setupObserver(favoritesViewModel);
        favoritesViewModel.refreshItems();
    }

    @Override
    protected void initDataBinding() {
        favoritesActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorites);

        favoritesViewModel = new FavoritesViewModel(getNavigator(), getSettingsManager(), getLogger());
        favoritesActivityBinding.setFavoritesViewModel(favoritesViewModel);
    }

    private void setupListScheduleView(RecyclerView listSchedule) {
        ScheduleAdapter adapter = new ScheduleAdapter(getNavigator());
        listSchedule.setAdapter(adapter);
        listSchedule.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof FavoritesViewModel) {
            ScheduleAdapter scheduleAdapter =
                    (ScheduleAdapter) favoritesActivityBinding.recyclerFavorites.getAdapter();

            FavoritesViewModel peopleViewModel = (FavoritesViewModel) observable;
            scheduleAdapter.setScheduleList(peopleViewModel.getSchedules());
        }
    }

}
