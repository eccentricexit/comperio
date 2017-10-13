package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.manaschaudhari.android_mvvm.adapters.ViewModelBinder;
import com.manaschaudhari.android_mvvm.utils.BindingUtils;
import com.manaschaudhari.android_mvvm.utils.Preconditions;
import com.rigel.comperio.R;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.viewmodel.ScheduleDetailsViewModel;

import org.parceler.Parcels;

public class ScheduleDetailActivity extends AppCompatActivity {

    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_detail);

        String extraId = this.getResources().getString(R.string.EXTRA_SCHEDULE_ID);
        Schedule schedule = Parcels.unwrap(getIntent().getParcelableExtra(extraId));

        getDefaultBinder().bind(binding, new ScheduleDetailsViewModel(schedule));
    }

    @Override
    protected void onDestroy() {
        getDefaultBinder().bind(binding, null);
        binding.executePendingBindings();
        super.onDestroy();
    }

    @NonNull
    private ViewModelBinder getDefaultBinder() {
        ViewModelBinder defaultBinder = BindingUtils.getDefaultBinder();
        Preconditions.checkNotNull(defaultBinder, "Default Binder");
        return defaultBinder;
    }

}
