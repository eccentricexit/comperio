package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.AdapterView;

import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.ActivitySubjectBinding;
import com.rigel.comperio.model.Subject;
import com.rigel.comperio.viewmodel.SubjectViewModel;

public class SubjectActivity extends BaseActivity {

    private static final String TAG = SubjectActivity.class.getSimpleName();

    @Override
    protected void initDataBinding() {
        final ActivitySubjectBinding subjectActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_subject);

        SubjectViewModel subjectViewModel = new SubjectViewModel(getNavigationManager(),
                getPersistanceManager(), getLogger());
        subjectActivityBinding.setSubjectViewModel(subjectViewModel);

        SubjectAdapter adapter = new SubjectAdapter(
                this,
                android.R.layout.simple_spinner_item,
                subjectActivityBinding.getSubjectViewModel().subjects
        );

        subjectActivityBinding.setSpinnerAdapter(adapter);

        subjectActivityBinding.subjectSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        subjectActivityBinding.getSubjectViewModel().userData.subject =
                                ((Subject) subjectActivityBinding.subjectSpinner.getSelectedItem()).name;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //no op
                    }
                }
        );

        if(subjectViewModel.userData==null){
            return;
        }

        int selection = -1;

        for(int i=0;i<subjectActivityBinding.getSpinnerAdapter().getCount();i++){
            Subject subject = (Subject) subjectActivityBinding.getSpinnerAdapter().getItem(i);
            if(subject.equals(subjectViewModel.userData.subject)){
                selection = i;
                break;
            }
        }

        if(selection!=-1) {
            subjectActivityBinding.subjectSpinner.setSelection(selection);
        }


    }

    @Override
    protected String getTag() {
        return TAG;
    }


}
