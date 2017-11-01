package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;

import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ActivityScheduleDetailBinding;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.viewmodel.ScheduleDetailViewModel;


public class ScheduleDetailActivity extends BaseActivity {

    private ActivityScheduleDetailBinding scheduleDetailActivityBinding;
    private ScheduleDetailViewModel scheduleDetailViewModel;

    @Override
    protected void initDataBinding() {
        scheduleDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);

        Schedule schedule = (Schedule) getIntent().getSerializableExtra(getString(R.string.EXTRA_SCHEDULE));
        scheduleDetailViewModel = new ScheduleDetailViewModel(getNavigator(), getPersistenceManager(),
                schedule, getLogger());

        scheduleDetailActivityBinding.setScheduleDetailViewModel(scheduleDetailViewModel);
    }

}
