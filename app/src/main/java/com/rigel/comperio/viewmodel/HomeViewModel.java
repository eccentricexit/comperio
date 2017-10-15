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

public class HomeViewModel implements ViewModel {

    private static final Observable<List<Schedule>> itemsSource;

    static {
        List<Schedule> items = new ArrayList<>();

        items.add(new Schedule("Thomas", "4.8", "$38.00", "", "6.5km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "German"));
        items.add(new Schedule("Chad", "3.8", "$48.00", "", "4.4km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "Spanish"));
        items.add(new Schedule("Anna", "4.5", "$28.00", "", "4.3km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "Java"));
        items.add(new Schedule("Bob", "4.2", "$68.00", "", "4.5km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "Kotlin"));
        items.add(new Schedule("Zed", "4.3", "$48.00", "", "3.5km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "French"));
        items.add(new Schedule("Maria", "4.3", "$33.00", "", "4.4km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "Mandarin"));
        items.add(new Schedule("Donald", "4.5", "$28.00", "", "2.5km", "+55 (12) 2323-4242", "Ever since I started lorem ipsum dolor amet mono aba did ololo afa afesdaf", "Solidity"));

        itemsSource = BehaviorSubject.createDefault(items);
    }

    public final Observable<List<ScheduleViewModel>> itemVms;

    public HomeViewModel(@NonNull final Navigator navigator) {
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

    public void updateItems() {

    }
}
