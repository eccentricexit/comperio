package com.rigel.comperio.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SeekBar;

import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.FragmentFiltersBinding;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.FiltersViewModel;

import java.util.Observable;
import java.util.Observer;

public class FiltersFragment extends BaseFragment implements Observer {

    public static final String TAG = "FilterFragmentTag";

    FragmentFiltersBinding fragmentFiltersBinding;
    FiltersViewModel filtersViewModel;

    public FiltersFragment() {
    }

    public static FiltersFragment newInstance() {
        return new FiltersFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        filtersViewModel = new FiltersViewModel(navigator, persistenceManager, logger);
        fragmentFiltersBinding.setFiltersViewModel(filtersViewModel);

        initSubjectSpinner();
        initSeekBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFiltersBinding = FragmentFiltersBinding.inflate(inflater, container, false);
        return fragmentFiltersBinding.getRoot();
    }

    private void initSeekBar() {
        fragmentFiltersBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                filtersViewModel.setMaxDistance(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        fragmentFiltersBinding.seekBar.setProgress(filtersViewModel.userData.maxDistance);
        fragmentFiltersBinding.textView4.setText(filtersViewModel.getFormattedDistance());
    }

    @Override
    public void onStart() {
        filtersViewModel.addObserver(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        filtersViewModel.save();
        filtersViewModel.deleteObserver(this);
        super.onStop();
    }


    private void initSubjectSpinner() {
        SubjectAdapter adapter = new SubjectAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                fragmentFiltersBinding.getFiltersViewModel().subjects
        );

        fragmentFiltersBinding.setSpinnerAdapter(adapter);

        int selection = -1;

        for(int i=0;i<fragmentFiltersBinding.getSpinnerAdapter().getCount();i++){
            Subject subject = (Subject) fragmentFiltersBinding.getSpinnerAdapter().getItem(i);
            if(filtersViewModel.userData.subject.equals(subject.name)){
                selection = i;
                break;
            }
            logger.log(subject.name);
        }

        if(selection!=-1) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    fragmentFiltersBinding.subjectSpinner.setSelection(1);
                }
            }, 100);
        }

        fragmentFiltersBinding.subjectSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        filtersViewModel.userData.subject =
                                ((Subject) fragmentFiltersBinding.subjectSpinner.getSelectedItem()).name;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //no op
                    }
                });
    }


    @Override
    protected void updateConnectivityStatus(Boolean isConnectedToInternet) {
        filtersViewModel.isConnectedToInternet = isConnectedToInternet;
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void update(Observable observable, Object o) {
        fragmentFiltersBinding.textView4.setText(filtersViewModel.getFormattedDistance());
    }
}
