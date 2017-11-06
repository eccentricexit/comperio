package com.rigel.comperio.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.adapters.ScheduleAdapter;
import com.rigel.comperio.databinding.FragmentFavoritesBinding;
import com.rigel.comperio.viewmodel.FavoritesViewModel;

import java.util.Observable;
import java.util.Observer;


public class FavoritesFragment extends BaseFragment implements Observer {

    FragmentFavoritesBinding fragmentFavoritesBinding;
    FavoritesViewModel favoritesViewModel;

    public FavoritesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        BaseActivity baseActivity = (BaseActivity) getActivity();
        navigator = baseActivity.getNavigator();
        logger = baseActivity.getLogger();
        persistenceManager = baseActivity.getPersistenceManager();

        favoritesViewModel = new FavoritesViewModel(navigator, persistenceManager, logger,
                getLoaderManager(), getContext());
        favoritesViewModel.addObserver(this);
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favoritesViewModel.initViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false);

        fragmentFavoritesBinding.setFavoritesViewModel(favoritesViewModel);
        fragmentFavoritesBinding.recyclerFavorites.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentFavoritesBinding.recyclerFavorites.setAdapter(new ScheduleAdapter(navigator, logger));

        return fragmentFavoritesBinding.getRoot();
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!(observable instanceof FavoritesViewModel)) {
            return;
        }

        ScheduleAdapter scheduleAdapter =
                (ScheduleAdapter) fragmentFavoritesBinding.recyclerFavorites.getAdapter();
        FavoritesViewModel favoritesViewModel = (FavoritesViewModel) observable;
        scheduleAdapter.setScheduleList(favoritesViewModel.getSchedules());
    }

}
