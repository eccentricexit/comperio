package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;

import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ActivityFreetimeBinding;
import com.rigel.comperio.viewmodel.FreeTimeViewModel;


public class FreeTimeActivity extends BaseActivity {

    @Override
    protected void initDataBinding() {
        ActivityFreetimeBinding activityFreeTimeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_freetime);
        FreeTimeViewModel filtersViewModel = new FreeTimeViewModel(getNavigator(),
                getPersistenceManager(), getLogger());

        activityFreeTimeBinding.setFreeTimeViewModel(filtersViewModel);
    }


}
