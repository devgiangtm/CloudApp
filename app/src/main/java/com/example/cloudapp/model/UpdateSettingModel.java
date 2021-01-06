package com.example.cloudapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateSettingModel {

    @SerializedName("autoUpdate")
    @Expose
    private boolean autoUpdate;

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    @Override
    public String toString() {
        return "UpdateSettingModel{" +
                "autoUpdate=" + autoUpdate +
                '}';
    }
}
