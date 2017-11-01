package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.FragmentHomeBinding;
import com.rigel.comperio.viewmodel.HomeViewModel;

import java.util.Observable;
import java.util.Observer;

public class HomeFragment extends Fragment implements Observer {

    Navigator navigator;
    DevUtils.Logger logger;
    PersistenceManager persistenceManager;

    FragmentHomeBinding fragmentHomeBinding;
    HomeViewModel homeViewModel;

    public HomeFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);

        homeViewModel = new HomeViewModel(navigator, persistenceManager,logger);
        homeViewModel.addObserver(this);
        fragmentHomeBinding.setHomeViewModel(homeViewModel);

        fragmentHomeBinding.recyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentHomeBinding.recyclerHome.setAdapter(new ScheduleAdapter(navigator,logger));

        homeViewModel.refreshItems();

        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!(observable instanceof HomeViewModel)) {
            return;
        }

        ScheduleAdapter scheduleAdapter = (ScheduleAdapter) fragmentHomeBinding.recyclerHome.getAdapter();
        HomeViewModel homeViewModel = (HomeViewModel) observable;
        scheduleAdapter.setScheduleList(homeViewModel.getSchedules());

    }

    public static HomeFragment newInstance(Navigator navigator, PersistenceManager persistenceManager,
                                           DevUtils.Logger logger) {
        HomeFragment homeFragment = new HomeFragment();

        homeFragment.setNavigator(navigator);
        homeFragment.setPersistenceManager(persistenceManager);
        homeFragment.setLogger(logger);

        return homeFragment;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public void setLogger(DevUtils.Logger logger) {
        this.logger = logger;
    }
}
