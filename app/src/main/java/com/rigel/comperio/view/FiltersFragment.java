package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.codetroopers.betterpickers.recurrencepicker.RecurrencePickerDialogFragment;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.FiltersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.manaschaudhari.android_mvvm.utils.BindingUtils.getDefaultBinder;


public class FiltersFragment extends BaseFragment {

    private static final String START_TIME_PICKER = "startTimePicker";
    private static final String END_TIME_PICKER = "endTimePicker";
    private static final String RECURRENCE_PICKER = "RECURRENCEPicker";
    private static final String LOG_TAG = FiltersFragment.class.getSimpleName();

    private ViewDataBinding binding;

    @BindView(R.id.btnSelectDaysOfTheWeek) Button btnSelectRecurrence;
    @BindView(R.id.txtStarTime) TextView txtStartTime;
    @BindView(R.id.txtEndTime) TextView txtEndTime;

    private String mRrule;

    private EventRecurrence mEventRecurrence = new EventRecurrence();

    public FiltersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container, false);

        Log.d(LOG_TAG,"onCreateStarted... binding");

        binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_filters);
        getDefaultBinder().bind(binding, viewModel);

        Log.d(LOG_TAG,"Bound. finishing onCreateView.");
        Log.d(LOG_TAG,"FilterViewModel data"+((FiltersViewModel) viewModel).subject);

        ButterKnife.bind(this, view);

        setupClickListeners();

        return view;
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

    public void startTimeSelect(){
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        ((FiltersViewModel)viewModel).setStartTime(hourOfDay,minute);
                    }
                })
                .setStartTime(10, 10)
                .setDoneText(getString(R.string.OK))
                .setCancelText(getString(R.string.Cancel))
                .setThemeDark();

        rtpd.show(getFragmentManager(), START_TIME_PICKER);
    }

    public void endTimeSelect(){
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        ((FiltersViewModel)viewModel).setEndTime(hourOfDay,minute);
                    }
                })
                .setStartTime(10, 10)
                .setDoneText(getString(R.string.OK))
                .setCancelText(getString(R.string.Cancel))
                .setThemeDark();

        rtpd.show(getFragmentManager(), END_TIME_PICKER);
    }

    public void recurrenceSelect(){
        FragmentManager fm = getFragmentManager();
        Bundle bundle = new Bundle();
        Time time = new Time();
        time.setToNow();
        bundle.putLong(RecurrencePickerDialogFragment.BUNDLE_START_TIME_MILLIS, time.toMillis(false));
        bundle.putString(RecurrencePickerDialogFragment.BUNDLE_TIME_ZONE, time.timezone);
        bundle.putString(RecurrencePickerDialogFragment.BUNDLE_RRULE, mRrule);
        bundle.putBoolean(RecurrencePickerDialogFragment.BUNDLE_HIDE_SWITCH_BUTTON, true);

        RecurrencePickerDialogFragment rpd = new RecurrencePickerDialogFragment();
        rpd.setArguments(bundle);
        rpd.setOnRecurrenceSetListener(new RecurrencePickerDialogFragment.OnRecurrenceSetListener() {
            @Override
            public void onRecurrenceSet(String rRule) {
                mRrule = rRule;
                if (mRrule == null) {
                    Log.w(LOG_TAG,"mRrule is null.");
                    return;
                }

                mEventRecurrence.parse(mRrule);
                ((FiltersViewModel)viewModel).setRecurrence(mEventRecurrence);
            }
        });
        rpd.show(fm, RECURRENCE_PICKER);
    }

}
