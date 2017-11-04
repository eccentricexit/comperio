package com.rigel.comperio.sync;

import com.rigel.comperio.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ComperioService {

    @GET("schedules/")
    Call<List<Schedule>> listSchedules();

}
