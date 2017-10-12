package com.rigel.comperio.View;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.ViewModel.SubjectViewModel;

import io.reactivex.annotations.NonNull;

public class SubjectActivity extends MvvmActivity {

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new SubjectViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject;
    }
}
