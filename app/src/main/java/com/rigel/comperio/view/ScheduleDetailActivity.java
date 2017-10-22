package com.rigel.comperio.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

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
        scheduleDetailViewModel = new ScheduleDetailViewModel(getNavigator(),getSettingsManager(),schedule);

        scheduleDetailActivityBinding.setScheduleDetailViewModel(scheduleDetailViewModel);
    }

    public static Intent launch(Context context, Schedule schedule) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        intent.putExtra(context.getString(R.string.EXTRA_SCHEDULE),schedule);
        return intent;
    }

}
