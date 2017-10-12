package com.rigel.comperio.view;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.SubjectViewModel;



public class SubjectActivity extends BaseActivity {

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new SubjectViewModel(getNavigator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject;
    }
}
