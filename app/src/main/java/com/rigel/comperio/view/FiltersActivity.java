package com.rigel.comperio.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ActivityFiltersBinding;
import com.rigel.comperio.viewmodel.FiltersViewModel;

public class FiltersActivity extends BaseActivity {

    @Override
    protected void initDataBinding() {
        ActivityFiltersBinding activityFiltersBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_filters);

        FiltersViewModel filtersViewModel = new FiltersViewModel(getNavigator(), getSettingsManager());

        activityFiltersBinding.setFiltersViewModel(filtersViewModel);
    }

}
