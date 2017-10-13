package com.rigel.comperio.viewmodel;

import android.support.annotation.NonNull;

import com.manaschaudhari.android_mvvm.ViewModel;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

public class FavoritesViewModel implements ViewModel{

    public final Observable<List<ScheduleViewModel>> itemVms;
    private static final Observable<List<Schedule>> itemsSource;

    static {
        List<Schedule> items = new ArrayList<>();

        items.add(new Schedule("Favorite 1","asdf","fff","fghd","dfghdf"));
        items.add(new Schedule("Favorite 2","f4f1","f1f1","hdfghdf","dfghdf"));

        itemsSource = BehaviorSubject.createDefault(items);
    }

    public FavoritesViewModel(@NonNull final Navigator navigator) {
        this.itemVms = itemsSource.map(new Function<List<Schedule>, List<ScheduleViewModel>>() {
            @Override
            public List<ScheduleViewModel> apply(List<Schedule> items) throws Exception {
                List<ScheduleViewModel> vms = new ArrayList<>();
                for (Schedule item : items) {
                    vms.add(new ScheduleViewModel(item, navigator));
                }
                return vms;
            }
        });
    }
}
