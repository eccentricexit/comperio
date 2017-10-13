package com.rigel.comperio;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.manaschaudhari.android_mvvm.adapters.ViewProvider;
import com.rigel.comperio.viewmodel.ScheduleViewModel;

public class ViewProviders {
    @NonNull
    public static ViewProvider getItemListing() {
        return new ViewProvider() {
            @Override
            public int getView(ViewModel vm) {
                if (vm instanceof ScheduleViewModel) {
                    return R.layout.row_schedule;
                }
                throw new UnsupportedOperationException();
            }
        };
    }
}
