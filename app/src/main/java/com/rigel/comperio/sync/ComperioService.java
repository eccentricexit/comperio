package com.rigel.comperio.sync;

import com.google.gson.JsonElement;
import com.rigel.comperio.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ComperioService {

    @GET("schedules/")
    Call<List<Schedule>> listSchedules(@Query("subject") String subject,
                                       @Query("maxDistance") Integer maxDist,
                                       @Query("lat")Float lat,
                                       @Query("lon")Float lon);

    @FormUrlEncoded
    @POST("schedules/")
    Call<Schedule> publishNewSchedule(@Field("teacherName") String teachName,
                                      @Field("teacherPicUrl") String Number,
                                      @Field("teachRating") Float teacherRating,
                                      @Field("teacherPhone") String teacherPhone,
                                      @Field("subjectName") String subjectName,
                                      @Field("loc") Float[] loc,
                                      @Field("hourPrice") Float hourPrice,
                                      @Field("teacherStory") String teachStory);

}
