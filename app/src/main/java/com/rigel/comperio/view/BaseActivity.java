package com.rigel.comperio.view;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.MvvmActivity;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.R;
import com.rigel.comperio.model.Schedule;

import org.parceler.Parcels;

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
            public void navigateToScheduleDetailsActivity(Schedule schedule) {
                Intent i = new Intent(BaseActivity.this, ScheduleDetailActivity.class);
                i.putExtra(
                        BaseActivity.this.getString(R.string.EXTRA_SCHEDULE_ID),
                        Parcels.wrap(schedule)
                );

                startActivity(i);
            }

            private void navigate(Class<?> destination) {
                Intent intent = new Intent(BaseActivity.this, destination);
                startActivity(intent);
            }
        };
    }

}
