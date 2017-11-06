package com.rigel.comperio;

import com.google.gson.Gson;
import com.rigel.comperio.model.Schedule;
import com.rigel.comperio.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class DevUtils {
    public static List<Schedule> getFakeHomeSchedules() {
        List<Schedule> fakeHomeSchedules = new ArrayList<>();

        Schedule schedule1 = new Schedule(
                "English", 23.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Celina Mars",
                "https://randomuser.me/api/portraits/women/20.jpg", "1234314919", 4.5f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        Schedule schedule2 = new Schedule(
                "English", 20.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Jessica McDonnald",
                "https://randomuser.me/api/portraits/women/22.jpg", "1234314919", 4.0f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        Schedule schedule3 = new Schedule(
                "English", 43.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Vlad Tron",
                "https://randomuser.me/api/portraits/men/22.jpg", "1234314919", 4.3f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        Schedule schedule4 = new Schedule(
                "English", 26.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Nathan Cypher",
                "https://randomuser.me/api/portraits/men/21.jpg", "1234314919", 4.2f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        Schedule schedule5 = new Schedule(
                "English", 43.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Alice Cypher",
                "https://randomuser.me/api/portraits/women/24.jpg", "1234314919", 4.5f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        fakeHomeSchedules.add(schedule1);
        fakeHomeSchedules.add(schedule2);
        fakeHomeSchedules.add(schedule3);
        fakeHomeSchedules.add(schedule4);
        fakeHomeSchedules.add(schedule5);

        return fakeHomeSchedules;
    }

    public static List<Schedule> getFakeFavoritesSchedules() {
        List<Schedule> fakeHomeSchedules = new ArrayList<>();

        Schedule schedule1 = new Schedule(
                "English", 23.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "Buterin Poon",
                "https://randomuser.me/api/portraits/men/20.jpg", "1234314919", 4.5f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        Schedule schedule2 = new Schedule(
                "English", 20.5f, "Su,Mo,Tue", new Float[]{0f, 0f}, "James Larimer",
                "https://randomuser.me/api/portraits/women/22.jpg", "1234314919", 4.0f,
                "Ever since I learned how to lorem ipsum I coudn't stop anymore.", 8, 0, 23, 30);

        fakeHomeSchedules.add(schedule1);
        fakeHomeSchedules.add(schedule2);

        return fakeHomeSchedules;
    }

    public static Subject[] getFakeSubjects() {
        Subject[] subjects = new Subject[]{
                new Subject(0, "English"),
                new Subject(1, "Spanish"),
                new Subject(2, "German"),
                new Subject(3, "Kotlin"),
        };

        return subjects;
    }

    public static <T> String toJson(T object) {
        return new Gson().toJson(object);
    }

    public interface Logger {
        void log(String message);

        void toast(String message);
    }

}
