package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.SettingsManager;
import com.rigel.comperio.databinding.FragmentFiltersBinding;
import com.rigel.comperio.viewmodel.FiltersViewModel;

public class FiltersFragment extends Fragment {

    Navigator navigator;
    DevUtils.Logger logger;
    SettingsManager settingsManager;

    FragmentFiltersBinding fragmentFiltersBinding;
    FiltersViewModel filtersViewModel;

    public FiltersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFiltersBinding = FragmentFiltersBinding.inflate(inflater,container,false);

        filtersViewModel = new FiltersViewModel(navigator,settingsManager,logger);
        fragmentFiltersBinding.setFiltersViewModel(filtersViewModel);

        return fragmentFiltersBinding.getRoot();
    }

    public static FiltersFragment newInstance(Navigator navigator,
                                              SettingsManager settingsManager,
                                              DevUtils.Logger logger) {

        FiltersFragment favoritesFragment = new FiltersFragment();

        favoritesFragment.setNavigator(navigator);
        favoritesFragment.setSettingsManager(settingsManager);
        favoritesFragment.setLogger(logger);

        return favoritesFragment;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setSettingsManager(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    public void setLogger(DevUtils.Logger logger) {
        this.logger = logger;
    }

}
