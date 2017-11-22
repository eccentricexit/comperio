package com.rigel.comperio.sync;

import com.rigel.comperio.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComperioService {

    @GET("schedules/")
    Call<List<Schedule>> listSchedules(@Query("subject") String subject,
                                       @Query("maxDistance") Integer maxDist,
                                       @Query("lat")Float lat,
                                       @Query("lon")Float lon);

}
