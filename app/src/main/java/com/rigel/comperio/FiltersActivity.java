package com.rigel.comperio;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.manaschaudhari.android_mvvm.ViewModel;

public class FiltersActivity extends MvvmActivity {

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new FiltersViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filters;
    }
}
