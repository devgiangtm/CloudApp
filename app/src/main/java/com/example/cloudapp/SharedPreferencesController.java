package com.example.cloudapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesController {

    public static final String mySharedPreferences = "MyPrefs";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    private static SharedPreferencesController instance;

    public static SharedPreferencesController with(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesController(context);
        }
        return instance;
    }

    public SharedPreferencesController(Context context) {
        sharedPreferences = context.getSharedPreferences(mySharedPreferences, Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveInt(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public void saveBoolean(String key, boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void clearAllData(Context context) {
        sharedPreferences = context.getSharedPreferences(mySharedPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
