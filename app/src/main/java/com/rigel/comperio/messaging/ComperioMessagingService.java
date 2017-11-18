package com.rigel.comperio.messaging;

import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rigel.comperio.sync.SyncAdapter;

public class ComperioMessagingService extends FirebaseMessagingService {
    private static final String TAG = ComperioMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Toast.makeText(this, "GOT FCM", Toast.LENGTH_SHORT).show();
//       Toast.makeText(this, "syncing!", Toast.LENGTH_SHORT).show();
        SyncAdapter.syncImmediately(this);
    }

}
