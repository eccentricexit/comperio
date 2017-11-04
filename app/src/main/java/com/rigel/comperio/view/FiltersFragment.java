package com.rigel.comperio.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.PersistenceManager;
import com.rigel.comperio.databinding.FragmentFiltersBinding;
import com.rigel.comperio.viewmodel.FiltersViewModel;

public class FiltersFragment extends BaseFragment {

    FragmentFiltersBinding fragmentFiltersBinding;
    FiltersViewModel filtersViewModel;

    public FiltersFragment() { }

    public static FiltersFragment newInstance() {
        return new FiltersFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        filtersViewModel = new FiltersViewModel(navigator, persistenceManager,logger);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFiltersBinding = FragmentFiltersBinding.inflate(inflater,container,false);
        fragmentFiltersBinding.setFiltersViewModel(filtersViewModel);

        return fragmentFiltersBinding.getRoot();
    }

}
