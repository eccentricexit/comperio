package com.rigel.comperio.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.ActivityHomeBinding;
import com.rigel.comperio.viewmodel.HomeViewModel;

import java.util.Observable;
import java.util.Observer;

public class HomeActivity extends BottomNavigationActivity implements Observer {

    private ActivityHomeBinding homeActivityBinding;
    private HomeViewModel homeViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListScheduleView(homeActivityBinding.recyclerHome);
        setupObserver(homeViewModel);
        homeViewModel.refreshItems();
    }

    private void initDataBinding() {
        homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = new HomeViewModel();
        homeActivityBinding.setHomeViewModel(homeViewModel);
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
        if (observable instanceof HomeViewModel) {
            ScheduleAdapter scheduleAdapter = (ScheduleAdapter) homeActivityBinding.recyclerHome.getAdapter();
            HomeViewModel peopleViewModel = (HomeViewModel) observable;
            scheduleAdapter.setScheduleList(peopleViewModel.getSchedules());
        }
    }

    @Override
    protected Context getContext() {
        return getContext();
    }
}

