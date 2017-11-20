package com.rigel.comperio.messaging;

import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rigel.comperio.sync.SyncAdapter;

public class ComperioMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        SyncAdapter.syncImmediately(this);
    }

}
