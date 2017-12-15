package com.rigel.comperio.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.rigel.comperio.R;
import com.rigel.comperio.databinding.ActivityScheduleDetailBinding;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.viewmodel.ScheduleDetailViewModel;
import com.squareup.picasso.Picasso;


public class ScheduleDetailActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;
    private static final String TAG = ScheduleDetailActivity.class.getSimpleName();

    private ActivityScheduleDetailBinding scheduleDetailActivityBinding;
    private ScheduleDetailViewModel scheduleDetailViewModel;

    @Override
    protected void initDataBinding() {
        scheduleDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);

        Schedule schedule = (Schedule) getIntent().getSerializableExtra(getString(R.string.EXTRA_SCHEDULE));
        scheduleDetailViewModel = new ScheduleDetailViewModel(getNavigationManager(), getPersistanceManager(),
                schedule, getLogger(),this);

        scheduleDetailActivityBinding.setScheduleDetailViewModel(scheduleDetailViewModel);

        Picasso.with(this)
                .load(scheduleDetailActivityBinding.getScheduleDetailViewModel().schedule.teacherPicUrl)
                .into(scheduleDetailActivityBinding.imageView);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode!=REQUEST_CODE){
            return;
        }

        showInterstitialAd();
    }

}
