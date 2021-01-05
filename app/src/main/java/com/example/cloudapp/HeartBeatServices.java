package com.example.cloudapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class HeartBeatServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("HeartBeatServices", "start");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myConnectionsRef = database.getReference("/deviceStatus/74ddbb7e199ffc9f9053575d044d27fc662c4540/status");
        final DatabaseReference deviceRef = database.getReference("/deviceStatus/74ddbb7e199ffc9f9053575d044d27fc662c4540");
        final DatabaseReference lastOnlineRef = database.getReference("/deviceStatus/74ddbb7e199ffc9f9053575d044d27fc662c4540/lastSeen");
        final DatabaseReference connectedRef = database.getReference(".info/connected");
        deviceRef.keepSynced(true);
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
//                    DatabaseReference con = myConnectionsRef.push();
                    // When this device disconnects, remove it
                    // When I disconnect, update the last time I was seen oline
                    myConnectionsRef.setValue(1);
                    // Add this device to my connections list
                    // this value could contain info about the device or a timestamp too
                    lastOnlineRef.setValue(ServerValue.TIMESTAMP);
//                    con.setValue(Boolean.TRUE);
                    Log.d("HeartBeatServices", "onDataChange: connected");
                }
                else {
                    Log.d("HeartBeatServices", "onDataChange: disconnected");
//                    DatabaseReference con = myConnectionsRef.push();
                    myConnectionsRef.onDisconnect().setValue(0);
                    // When I disconnect, update the last time I was seen oline
                    lastOnlineRef.onDisconnect().setValue(ServerValue.TIMESTAMP);
//                    con.setValue(Boolean.FALSE);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("HeartBeatServices", "Listener was cancelled at .info/connected");
            }
        });
        return START_STICKY;
    }
}
