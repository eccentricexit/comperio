package com.rigel.comperio.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.rigel.comperio.DevUtils;
import com.rigel.comperio.Navigator;
import com.rigel.comperio.model.Schedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;

public class HomeViewModel extends Observable implements Serializable {

    private List<Schedule> schedules;
    private Context context;

    public HomeViewModel(Context context) {
        this.context = context;
        this.schedules = new ArrayList<>();

    }

    public void refreshItems() {
        schedules.addAll(DevUtils.getFakeHomeSchedules());
        setChanged();
        notifyObservers();
    }

    public List<Schedule> getSchedules(){
        return this.schedules;
    }

    public void onClickFabLoad(View view) {
        Toast.makeText(context, "TESSST", Toast.LENGTH_SHORT).show();
    }


    public void reset() {

    }
}
