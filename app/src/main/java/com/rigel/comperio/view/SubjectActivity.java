package com.rigel.comperio.view;

import android.databinding.DataBindingUtil;


import com.rigel.comperio.R;
import com.rigel.comperio.adapters.SubjectAdapter;
import com.rigel.comperio.databinding.ActivitySubjectBinding;
import com.rigel.comperio.viewmodel.SubjectViewModel;

public class SubjectActivity extends BaseActivity {

    @Override
    protected void initDataBinding() {
        ActivitySubjectBinding subjectActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_subject);

        SubjectViewModel subjectViewModel = new SubjectViewModel(getNavigator(),
                getPersistenceManager(), getLogger());
        subjectActivityBinding.setSubjectViewModel(subjectViewModel);

        SubjectAdapter adapter = new SubjectAdapter(
                this,
                android.R.layout.simple_spinner_item,
                subjectActivityBinding.getSubjectViewModel().subjects
        );

        subjectActivityBinding.setSpinnerAdapter(adapter);
    }


}
