package com.rigel.comperio.messaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class ComperioInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = ComperioInstanceIdService.class.getSimpleName();
    private static final String UPDATE_DB_TOPIC = "updateDb";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);

        // Once a token is generated, we subscribe to topic.
        FirebaseMessaging.getInstance()
                .subscribeToTopic(UPDATE_DB_TOPIC);
    }
}
