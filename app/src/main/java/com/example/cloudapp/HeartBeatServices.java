package com.example.cloudapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cloudapp.model.AllConfig;
import com.example.cloudapp.model.CDCSettingModel;
import com.example.cloudapp.model.Command;
import com.example.cloudapp.model.EmailSettingModel;
import com.example.cloudapp.model.MipsConfig;
import com.example.cloudapp.model.PrintSettingModel;
import com.example.cloudapp.model.UpdateSettingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.List;

import static com.example.cloudapp.Constant.CDC_MOCKUP;
import static com.example.cloudapp.Constant.CONFIG_MOCKUP;
import static com.example.cloudapp.Constant.EMAIL_MOCKUP_;
import static com.example.cloudapp.Constant.MIPS_DATA_KEY;
import static com.example.cloudapp.Constant.PRINT_MOCKUP;
import static com.example.cloudapp.Constant.RESTART;
import static com.example.cloudapp.Constant.SHUTDOWN;
import static com.example.cloudapp.Constant.UPDATE_CONFIG;
import static com.example.cloudapp.Constant.UPDATE_MOCKUP;

public class HeartBeatServices extends Service {
    private Dao<Command, Long> commandDAO;
    private DatabaseReference deviceCommandResult = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        commandDAO = DAOManager.getCommandDao(getBaseContext());
        String uuid = SharedPreferencesController.with(getBaseContext()).getString("uuid").equals("") ?
                intent.getStringExtra("uuid")
                : SharedPreferencesController.with(getBaseContext()).getString("uuid");
        SharedPreferencesController.with(getBaseContext()).saveString("uuid", uuid);
        int tenantid = SharedPreferencesController.with(getBaseContext()).getInt("tenantid") == -1 ?
                intent.getIntExtra("tenantid", -1)
                : SharedPreferencesController.with(getBaseContext()).getInt("tenantid");
        SharedPreferencesController.with(getBaseContext()).saveInt("tenantid", tenantid);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myConnectionsRef = database.getReference("/deviceStatus/" + tenantid + "/" + uuid + "/status");
        final DatabaseReference deviceRef = database.getReference("/deviceStatus/" + tenantid + "/" + uuid);
        final DatabaseReference lastOnlineRef = database.getReference("/deviceStatus/" + tenantid + "/" + uuid + "/lastSeen");
        final DatabaseReference deviceCommand = database.getReference("/deviceCommand/" + tenantid + "/" + uuid);
        deviceCommandResult = database.getReference("/deviceCommandResult/" + tenantid + "/" + uuid);
        deviceCommand.keepSynced(true);

        final DatabaseReference connectedRef = database.getReference(".info/connected");
        deviceRef.keepSynced(true);
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    // When this device disconnects, remove it
                    // When I disconnect, update the last time I was seen oline
                    myConnectionsRef.setValue(1);
                    // Add this device to my connections list
                    // this value could contain info about the device or a timestamp too
                    lastOnlineRef.setValue(ServerValue.TIMESTAMP);
                    Log.d("HeartBeatServices", "onDataChange: connected");
                } else {
                    Log.d("HeartBeatServices", "onDataChange: disconnected");
                    myConnectionsRef.onDisconnect().setValue(0);
                    // When I disconnect, update the last time I was seen oline
                    lastOnlineRef.onDisconnect().setValue(ServerValue.TIMESTAMP);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("HeartBeatServices", "Listener was cancelled at .info/connected");
            }
        });

        deviceCommand.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Command command = snapshot.getValue(Command.class);
                if (command != null) {
                    switch (command.getCommand()) {
                        case UPDATE_CONFIG:
                            updateConfigCommand(command);
                            break;
                        case SHUTDOWN:
                            shutdown(command);
                            break;
                        case RESTART:
                            restart(command);
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return START_STICKY;
    }

    private void shutdown(Command command){
        try {
            PreparedQuery<Command> preparedQuery = commandDAO.queryBuilder().where().eq("createdAt", command.getCreatedAt()).prepare();
            List<Command> commandList = commandDAO.query(preparedQuery);
            if (commandList.size() == 0) {
                commandDAO.create(command);
                //todo: get config from devices
                if(deviceCommandResult != null){
                    deviceCommandResult.child("result").setValue("Shutdown success");
                }
                Utils.shutdownDevice();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void restart(Command command){
        try {
            PreparedQuery<Command> preparedQuery = commandDAO.queryBuilder().where().eq("createdAt", command.getCreatedAt()).prepare();
            List<Command> commandList = commandDAO.query(preparedQuery);
            if (commandList.size() == 0) {
                commandDAO.create(command);
                //todo: get config from devices
                if(deviceCommandResult != null){
                    deviceCommandResult.child("result").setValue("Restart success");
                }
                Utils.restartDevice();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateConfigCommand(Command command) {
        try {
            PreparedQuery<Command> preparedQuery = commandDAO.queryBuilder().where().eq("createdAt", command.getCreatedAt()).prepare();
            List<Command> commandList = commandDAO.query(preparedQuery);
            if (commandList.size() == 0) {
                commandDAO.create(command);
                //todo: get config from devices
                String result = CONFIG_MOCKUP;
                JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
                String data = jsonObject.get(MIPS_DATA_KEY).getAsString();
                String email = EMAIL_MOCKUP_;
                String print = PRINT_MOCKUP;
                String cdc = CDC_MOCKUP;
                String update = UPDATE_MOCKUP;
                Gson gson = new Gson();
                MipsConfig mipsConfig = gson.fromJson(data, MipsConfig.class);
                EmailSettingModel emailSettingModel = gson.fromJson(email, EmailSettingModel.class);
                PrintSettingModel printSettingModel = gson.fromJson(print, PrintSettingModel.class);
                CDCSettingModel cdcSettingModel = gson.fromJson(cdc, CDCSettingModel.class);
                UpdateSettingModel updateSettingModel = gson.fromJson(update, UpdateSettingModel.class);

                AllConfig allConfig = new AllConfig(mipsConfig, emailSettingModel, printSettingModel, cdcSettingModel, updateSettingModel);
                String json = gson.toJson(allConfig);
                if(deviceCommandResult != null){
                    deviceCommandResult.child("result").setValue(json);
                }

                Log.d("updateConfigCommand", "updateConfigCommand: " + data);
                // TODO: execute command
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
