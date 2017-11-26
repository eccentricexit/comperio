package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;

import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.ActivityNewScheduleBinding;
import com.rigel.comperio.viewmodel.NewScheduleViewModel;

public class NewScheduleActivity extends BaseActivity {

    private NewScheduleViewModel newScheduleViewModel;
    private ActivityNewScheduleBinding newScheduleActivityBinding;

    @Override
    protected void initDataBinding() {
        newScheduleActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_schedule);

        newScheduleViewModel = new NewScheduleViewModel(getNavigator(), getPersistenceManager(),
                getLogger());

        newScheduleActivityBinding.setNewScheduleViewModel(newScheduleViewModel);

        SubjectAdapter adapter = new SubjectAdapter(
                this,
                android.R.layout.simple_spinner_item,
                newScheduleActivityBinding.getNewScheduleViewModel().subjects
        );

        newScheduleActivityBinding.setSpinnerAdapter(adapter);
    }

}
