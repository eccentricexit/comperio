package com.rigel.comperio.sync;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComperioFactory {
    private static final String PROTOCOL = "http";
    private static final String HOST = "192.168.0.13";
    private static final String PORT = "3000";
    private static final String API_VERSION = "v1";

    private static final String BASE_URL = PROTOCOL + "://"+HOST + ":"+PORT+"/"+API_VERSION+"/";

    public static ComperioService create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ComperioService.class);
    }
}
