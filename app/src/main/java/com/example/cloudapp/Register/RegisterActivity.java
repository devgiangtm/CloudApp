package com.example.cloudapp.Register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cloudapp.HeartBeatServices;
import com.example.cloudapp.R;
import com.example.cloudapp.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SignatureException;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {
    private static final String REGISTER_URL = "";
    Button btnRegister;
    Button btnRevoke;
    EditText etApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        btnRegister = findViewById(R.id.btnRegister);
        btnRevoke = findViewById(R.id.btnRevoke);
        etApiKey = findViewById(R.id.etApiKey);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        registerDevice(Objects.requireNonNull(etApiKey.getText()).toString());
                    }
                }).start();
            }
        });
        btnRevoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String postResult = post(REGISTER_URL, etApiKey.getText().toString(), "");
                            ResponseObj responseObj = new Gson().fromJson(postResult, ResponseObj.class);
                            switch (responseObj.getStatus()) {
                                //code from API
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

//    private static String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {
//
//        SecretKey secretKey = null;
//
//        byte[] keyBytes = keyString.getBytes();
//        secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
//
//        Mac mac = Mac.getInstance("HmacSHA1");
////
////        mac.init(secretKey);
////
////        byte[] text = baseString.getBytes();
//        try {
//            SecretKeySpec signingKey = new SecretKeySpec(keyString.getBytes(), HMAC_SHA1_ALGORITHM);
//            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//            mac.init(signingKey);
//            byte[] rawHmac = mac.doFinal(rawMacAddress.getBytes());
//            result = new String(Base64.encode(rawHmac));
//        } catch (Exception e) {
//            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
//        }
//
//        return new String(Base64.encodeBase64(mac.doFinal(text))).trim();
//    }

    private void registerDevice(String apikey) {
        try {
            RegisterInfo registerInfo = new RegisterInfo();
            registerInfo.setIpAddress(Utils.getIPAddress(true));
            registerInfo.setMacAddress(Utils.getMacAddr());
            HmacUtils mac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, apikey);
            String hmacSHA1Result = String.valueOf(Hex.encodeHex(mac.hmac(Utils.getMacAddr())));
            registerInfo.setHashingMac(hmacSHA1Result);
            String postResult = post(REGISTER_URL, apikey, new Gson().toJson(registerInfo));
            ResponseObj responseObj = new Gson().fromJson(postResult, ResponseObj.class);
            switch (responseObj.getStatus()) {
                case 400: //Your MAC or IP is not valid
                    break;
                case 401: //Your api key is not correct
                    break;
                case 201: //Register successfully
                    Intent intent = new Intent(this, HeartBeatServices.class);
                    intent.putExtra("hmac",registerInfo.getHashingMac());
                    startService(intent);
                    break;
                case 500: //Undermined Internal Server Error. Pls try again
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String post(String url, String apiKey, String json) throws IOException {
        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .addHeader("Api-Key", apiKey)
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
//    private String generateHashedMac(String hashKey, String rawMacAddress) throws SignatureException {
//        final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
//        String result;
//        try {
//            SecretKeySpec signingKey = new SecretKeySpec(hashKey.getBytes(), HMAC_SHA1_ALGORITHM);
//            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//            mac.init(signingKey);
//            byte[] rawHmac = mac.doFinal(rawMacAddress.getBytes());
//            result = new String(Base64.encode(rawHmac));
//        } catch (Exception e) {
//            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
//        }
//        return result;
//    }
}
