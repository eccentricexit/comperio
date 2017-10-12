package com.rigel.comperio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.manaschaudhari.android_mvvm.ViewModel;

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
