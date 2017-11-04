package com.rigel.comperio.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.FragmentHomeBinding;
import com.rigel.comperio.viewmodel.HomeViewModel;

import java.util.Observable;
import java.util.Observer;

public class HomeFragment extends BaseFragment implements Observer {

    FragmentHomeBinding fragmentHomeBinding;
    HomeViewModel homeViewModel;

    public HomeFragment() {  }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeViewModel = new HomeViewModel(navigator, persistenceManager,logger,
                getLoaderManager(),getContext());
        homeViewModel.addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);

        fragmentHomeBinding.setHomeViewModel(homeViewModel);
        fragmentHomeBinding.recyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentHomeBinding.recyclerHome.setAdapter(new ScheduleAdapter(navigator,logger));

        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel.initViewModel();
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

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

}
