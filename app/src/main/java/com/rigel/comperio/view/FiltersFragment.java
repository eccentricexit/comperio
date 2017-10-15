package com.rigel.comperio.view;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.codetroopers.betterpickers.recurrencepicker.EventRecurrence;
import com.codetroopers.betterpickers.recurrencepicker.RecurrencePickerDialogFragment;
import com.manaschaudhari.android_mvvm.utils.BindingUtils;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.FiltersViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FiltersFragment extends BaseFragment {

    private static final String START_TIME_PICKER = "startTimePicker";
    private static final String END_TIME_PICKER = "endTimePicker";
    private static final String RECURRENCE_PICKER = "recurrencePicker";
    private static final String LOG_TAG = FiltersFragment.class.getSimpleName();

    @BindView(R.id.btnSelectDaysOfTheWeek) Button btnSelectRecurrence;
    @BindView(R.id.txtStarTime) TextView txtStartTime;
    @BindView(R.id.txtEndTime) TextView txtEndTime;
    @BindView(R.id.subjectSpinner)
    Spinner subjectSpinner;

    private String rRule;
    private FiltersViewModel viewModel;

    private EventRecurrence eventRecurrence = new EventRecurrence();

    public FiltersFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = (FiltersViewModel) super.viewModel;

        Log.d(LOG_TAG, "Initialized " + viewModel.getSettingsManager().getPreferencesInitialized());
        Log.d(LOG_TAG, "Subject Id from viewmodel: " + viewModel.subject.get());

        setupSpinner();
        setupClickListeners();

        return view;
    }

    private void setupSpinner() {
        subjectSpinner.setAdapter(
                new SubjectAdapter(getContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        viewModel.subjects)
        );

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.subject.set(((Subject) subjectSpinner.getSelectedItem()).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing.
            }
        });

        subjectSpinner.setSelection(getSelectedItemPosition(viewModel.subject.get(), viewModel.subjects));
    }

    private int getSelectedItemPosition(Long selectedId, Subject[] subjects) {
        for (int i = 0; i < subjects.length; i++) {
            if (selectedId.equals(subjects[i].getId())) {
                return i;
            }
        }

        throw new IndexOutOfBoundsException();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_filters;
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
                        viewModel.setStartTime(hourOfDay, minute);
                    }
                })
                .setStartTime(viewModel.getStartHour(), viewModel.getStartMinute())
                .setDoneText(getString(R.string.lblOK))
                .setCancelText(getString(R.string.lblCancel))
                .setThemeDark();

        rtpd.show(getFragmentManager(), START_TIME_PICKER);
    }

    public void endTimeSelect(){
        RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                .setOnTimeSetListener(new RadialTimePickerDialogFragment.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
                        viewModel.setEndTime(hourOfDay, minute);
                    }
                })
                .setStartTime(viewModel.getEndHour(), viewModel.getEndMinute())
                .setDoneText(getString(R.string.lblOK))
                .setCancelText(getString(R.string.lblCancel))
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
        bundle.putString(RecurrencePickerDialogFragment.BUNDLE_RRULE, rRule);
        bundle.putBoolean(RecurrencePickerDialogFragment.BUNDLE_HIDE_SWITCH_BUTTON, true);

        RecurrencePickerDialogFragment rpd = new RecurrencePickerDialogFragment();
        rpd.setArguments(bundle);
        rpd.setOnRecurrenceSetListener(new RecurrencePickerDialogFragment.OnRecurrenceSetListener() {
            @Override
            public void onRecurrenceSet(String rRule) {
                FiltersFragment.this.rRule = rRule;
                if (FiltersFragment.this.rRule == null) {
                    Log.w(LOG_TAG, "rRule is null.");
                    return;
                }

                eventRecurrence.parse(FiltersFragment.this.rRule);
                viewModel.setRecurrence(eventRecurrence);
            }
        });
        rpd.show(fm, RECURRENCE_PICKER);
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroy");
        viewModel.persistSettings();
        super.onDestroyView();
    }
}
