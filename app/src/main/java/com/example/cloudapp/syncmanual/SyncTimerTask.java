package com.example.cloudapp.syncmanual;

import com.example.cloudapp.model.AllConfig;
import com.example.cloudapp.model.CDCSettingModel;
import com.example.cloudapp.model.EmailSettingModel;
import com.example.cloudapp.model.MipsConfig;
import com.example.cloudapp.model.PrintSettingModel;
import com.example.cloudapp.model.UpdateSettingModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.cloudapp.Constant.CDC_MOCKUP;
import static com.example.cloudapp.Constant.CONFIG_MOCKUP;
import static com.example.cloudapp.Constant.EMAIL_MOCKUP_;
import static com.example.cloudapp.Constant.MIPS_DATA_KEY;
import static com.example.cloudapp.Constant.PRINT_MOCKUP;
import static com.example.cloudapp.Constant.UPDATE_CONFIG;
import static com.example.cloudapp.Constant.UPDATE_MOCKUP;

public class SyncTimerTask extends TimerTask {
    private static final String SEND_CONFIG_URL = "";

    @Override
    public void run() {
        //Todo: getconfig from mips

        String result = CONFIG_MOCKUP;
        String email = EMAIL_MOCKUP_;
        String print = PRINT_MOCKUP;
        String cdc = CDC_MOCKUP;
        String update = UPDATE_MOCKUP;

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        String data = jsonObject.get(MIPS_DATA_KEY).getAsString();
        MipsConfig mipsConfig = gson.fromJson(data, MipsConfig.class);
        EmailSettingModel emailSettingModel = gson.fromJson(email, EmailSettingModel.class);
        PrintSettingModel printSettingModel = gson.fromJson(print, PrintSettingModel.class);
        CDCSettingModel cdcSettingModel = gson.fromJson(cdc, CDCSettingModel.class);
        UpdateSettingModel updateSettingModel = gson.fromJson(update, UpdateSettingModel.class);

        AllConfig allConfig = new AllConfig(mipsConfig, emailSettingModel, printSettingModel, cdcSettingModel, updateSettingModel);

        String json = gson.toJson(allConfig);
//        try {
//            String resultPost = post(SEND_CONFIG_URL, json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private String post(String url, String json) throws IOException {
        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
