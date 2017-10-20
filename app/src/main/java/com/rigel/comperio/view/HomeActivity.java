package com.rigel.comperio.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.rigel.comperio.R;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.ActivityHomeBinding;
import com.rigel.comperio.viewmodel.HomeViewModel;

import java.util.Observable;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding homeActivityBinding;
    private HomeViewModel homeViewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListScheduleView(homeActivityBinding.recyclerHome);
        setupObserver(homeViewModel);
    }

    private void initDataBinding() {
        homeActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeViewModel = new HomeViewModel(this);
        homeActivityBinding.setMainViewModel(homeViewModel);
    }

    private void setupListScheduleView(RecyclerView listSchedule) {
        ScheduleAdapter adapter = new ScheduleAdapter();
        listSchedule.setAdapter(adapter);
        listSchedule.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        homeViewModel.reset();
    }

    private void startActivityActionView() {
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ScheduleFactory.PROJECT_URL)));
    }

    @Override public void update(Observable observable, Object data) {
        if (observable instanceof HomeViewModel) {
            ScheduleAdapter peopleAdapter = (ScheduleAdapter) homeActivityBinding.recyclerHome.getAdapter();
            HomeViewModel peopleViewModel = (HomeViewModel) observable;
            peopleAdapter.setScheduleList(peopleViewModel.getSchedules());
        }
    }
}

