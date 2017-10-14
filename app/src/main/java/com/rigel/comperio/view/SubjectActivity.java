package com.rigel.comperio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.SubjectViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubjectActivity extends BaseActivity {

    @BindView(R.id.subjectSpinner)
    Spinner subjectSpinner;

    private SubjectViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.subject.set(subjectSpinner.getSelectedItem().toString());
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
        viewModel = new SubjectViewModel(getNavigator(), getSettingsManager(), getMessageHelper());
        return viewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject;
    }
}
