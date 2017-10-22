package com.rigel.comperio;

import com.google.gson.Gson;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class DevUtils {
    public static List<Schedule> getFakeHomeSchedules() {
        List<Schedule> fakeHomeSchedules = new ArrayList<>();

        // TODO: Add fake items

        return fakeHomeSchedules;
    }

    public static List<Schedule> getFakeFavoritesSchedules() {
        List<Schedule> fakeHomeSchedules = new ArrayList<>();

        // TODO: Add fake items

        return fakeHomeSchedules;
    }

    public static Subject[] getFakeSubjects() {
        Subject[] subjects = new Subject[]{
                new Subject(0,"English"),
                new Subject(1,"Spanish"),
                new Subject(2,"German"),
                new Subject(3,"Kotlin"),
        };

        return subjects;
    }

    public static <T> String toJson(T object){
        return new Gson().toJson(object);
    }

    public interface Logger {
        void log(String message);
        void toast(String message);
    }

}
