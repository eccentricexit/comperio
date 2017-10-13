package com.rigel.comperio.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.rigel.comperio.Navigator;

public abstract class BaseActivity extends MvvmActivity {

    @NonNull
    protected Navigator getNavigator() {
        return new Navigator() {

            @Override
            public void navigateToFreeTimeActivity() {
                navigate(FreeTimeActivity.class);
            }

            @Override
            public void navigateToMainActivity() {
                navigate(MainActivity.class);
            }

            @Override
            public void navigateToScheduleDetailsActivity() {
                navigate(ScheduleDetailActivity.class);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };
    }

}
