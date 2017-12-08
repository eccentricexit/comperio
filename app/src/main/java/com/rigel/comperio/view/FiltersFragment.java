package com.rigel.comperio.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.FragmentFiltersBinding;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.FiltersViewModel;

public class FiltersFragment extends BaseFragment {

    FragmentFiltersBinding fragmentFiltersBinding;
    FiltersViewModel filtersViewModel;

    public FiltersFragment() {
    }

    public static FiltersFragment newInstance() {
        return new FiltersFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        filtersViewModel = new FiltersViewModel(navigator, persistenceManager, logger);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFiltersBinding = FragmentFiltersBinding.inflate(inflater, container, false);
        fragmentFiltersBinding.setFiltersViewModel(filtersViewModel);

        initSubjectSpinner();

        return fragmentFiltersBinding.getRoot();
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
            if(subject.equals(filtersViewModel.userData.subject)){
                selection = i;
                break;
            }
        }

        if(selection!=-1) {
            fragmentFiltersBinding.subjectSpinner.setSelection(selection);
        }

        fragmentFiltersBinding.subjectSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        fragmentFiltersBinding.getFiltersViewModel().userData.subject =
                                ((Subject) fragmentFiltersBinding.subjectSpinner.getSelectedItem()).name;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //no op
                    }
                });
    }

    @Override
    public void onStop() {
        filtersViewModel.save(null);
        super.onStop();
    }

    @Override
    protected void updateViewModel(Boolean isConnectedToInternet) {
        filtersViewModel.isConnectedToInternet = isConnectedToInternet;
    }
}
