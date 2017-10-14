package com.rigel.comperio.view;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.R;
import com.rigel.comperio.viewmodel.FreeTimeViewModel;

public class FreeTimeActivity extends BaseActivity {

    @NonNull
    @Override
    protected ViewModel createViewModel() {
        return new FreeTimeViewModel(getNavigator());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_freetime;
    }
}
