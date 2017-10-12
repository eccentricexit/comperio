package com.rigel.comperio.View;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.rigel.comperio.Navigator;

public abstract class BaseActivity extends MvvmActivity {

    @NonNull
    protected Navigator getNavigator() {
        return new Navigator() {

            @Override
            public void navigateToScheduleActivity() {
                navigate(ScheduleActivity.class);
            }

            @Override
            public void navigateToSubjectActivity() {
                navigate(SubjectActivity.class);
            }

            @Override
            public void navigateToMainActivity() {
                navigate(MainActivity.class);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };
    }
}
