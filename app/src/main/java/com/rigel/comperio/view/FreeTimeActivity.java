package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.codetroopers.betterpickers.recurrencepicker.RecurrencePickerDialogFragment;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.FreeTimeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FreeTimeActivity extends BaseActivity {

    private static final String LOG_TAG = FreeTimeActivity.class.getSimpleName();

    private static final String START_TIME_PICKER = "startTimePicker";
    private static final String END_TIME_PICKER = "endTimePicker";
    private static final String RECURRENCE_PICKER = "RECURRENCEPicker";

    @BindView(R.id.btnSelectRecurrence)
    Button btnSelectRecurrence;
    @BindView(R.id.txtStarTime)
    TextView txtStartTime;
    @BindView(R.id.txtEndTime)
    TextView txtEndTime;

    private FreeTimeViewModel viewModel;
    private String rRule;
    private EventRecurrence eventRecurrence = new EventRecurrence();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setupClickListeners();
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        viewModel = new FreeTimeViewModel(getNavigator(), getSettingsManager());
        return viewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_freetime;
    }

    private void setupClickListeners() {
        btnSelectRecurrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recurrenceSelect();
            }
        });

        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimeSelect();
            }
        });

        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endTimeSelect();
            }
        });
    }

    public void startTimeSelect() {
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        viewModel.setStartTime(hourOfDay, minute);
                    }
                })
                .setStartTime(10, 10)
                .setDoneText(getString(R.string.lblOK))
                .setCancelText(getString(R.string.lblCancel))
                .setThemeDark();

        rtpd.show(getSupportFragmentManager(), START_TIME_PICKER);
    }

    public void endTimeSelect() {
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        viewModel.setEndTime(hourOfDay, minute);
                    }
                })
                .setStartTime(10, 10)
                .setDoneText(getString(R.string.lblOK))
                .setCancelText(getString(R.string.lblCancel))
                .setThemeDark();

        rtpd.show(getSupportFragmentManager(), END_TIME_PICKER);
    }

    public void recurrenceSelect() {
        FragmentManager fm = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        Time time = new Time();
        time.setToNow();
        bundle.putLong(RecurrencePickerDialogFragment.BUNDLE_START_TIME_MILLIS, time.toMillis(false));
        bundle.putString(RecurrencePickerDialogFragment.BUNDLE_TIME_ZONE, time.timezone);
        bundle.putString(RecurrencePickerDialogFragment.BUNDLE_RRULE, rRule);
        bundle.putBoolean(RecurrencePickerDialogFragment.BUNDLE_HIDE_SWITCH_BUTTON, true);

        RecurrencePickerDialogFragment rpd = new RecurrencePickerDialogFragment();
        rpd.setArguments(bundle);
        rpd.setOnRecurrenceSetListener(new RecurrencePickerDialogFragment.OnRecurrenceSetListener() {
            @Override
            public void onRecurrenceSet(String rRule) {
                FreeTimeActivity.this.rRule = rRule;
                if (FreeTimeActivity.this.rRule == null) {
                    Log.w(LOG_TAG, "rRule is null.");
                    return;
                }

                eventRecurrence.parse(FreeTimeActivity.this.rRule);
                viewModel.setRecurrence(eventRecurrence);
            }
        });
        rpd.show(fm, RECURRENCE_PICKER);
    }
}
