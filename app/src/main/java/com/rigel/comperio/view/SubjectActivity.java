package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.FiltersViewModel;
import com.rigel.comperio.viewmodel.SubjectViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubjectActivity extends BaseActivity {

    private static final String LOG_TAG = SubjectActivity.class.getSimpleName();
    @BindView(R.id.subjectSpinner)
    Spinner subjectSpinner;

    private SubjectViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Log.d(LOG_TAG, "initialized  " + getSettingsManager().getPreferencesInitialized());
        Log.d(LOG_TAG, "saved subject: " + getSettingsManager().getSubject());

        if (getSettingsManager().getPreferencesInitialized()) {
            getNavigator().navigateToMainActivity();
        }

        setupSpinner();
    }

    private void setupSpinner() {
        subjectSpinner.setAdapter(
                new SubjectAdapter(this, R.layout.support_simple_spinner_dropdown_item, viewModel.subjects)
        );

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Long id = ((Subject) subjectSpinner.getSelectedItem()).getId();
                Log.d(LOG_TAG, "selected a subject:" + id);
                viewModel.subject.set(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing.
            }
        });
    }

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        viewModel = new SubjectViewModel(getNavigator(), getSettingsManager());
        return viewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject;
    }
}
