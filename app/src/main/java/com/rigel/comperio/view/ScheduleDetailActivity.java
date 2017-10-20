package com.rigel.comperio.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ActivityScheduleDetailBinding;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.viewmodel.ScheduleDetailViewModel;


public class ScheduleDetailActivity extends AppCompatActivity {

    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

    private ActivityScheduleDetailBinding scheduleDetailActivityBinding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scheduleDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);

        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, Schedule schedule) {
        Intent intent = new Intent(context, ScheduleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE,schedule);
        return intent;
    }

    private void getExtrasFromIntent() {
        Schedule schedule = (Schedule) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        ScheduleDetailViewModel scheduleDetailViewModel = new ScheduleDetailViewModel(schedule);
        scheduleDetailActivityBinding.setScheduleDetailViewModel(scheduleDetailViewModel);
    }

}
