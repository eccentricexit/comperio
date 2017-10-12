package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.FiltersViewModel;


public class FiltersFragment extends BaseFragment {

    public FiltersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

}
