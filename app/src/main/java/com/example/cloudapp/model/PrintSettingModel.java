package com.example.cloudapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  PrintSettingModel {

    @SerializedName("printBadge")
    @Expose
    private boolean printBadge;

    @SerializedName("registeredUser")
    @Expose
    private boolean registeredUser;

    @SerializedName("registeredVisitor")
    @Expose
    private boolean registeredVisitor;

    @SerializedName("unregisteredVisitor")
    @Expose
    private boolean unregisteredVisitor;

    @SerializedName("scanImage")
    @Expose
    private boolean scanImage;

    @SerializedName("userName")
    @Expose
    private boolean userName;

    @SerializedName("userType")
    @Expose
    private boolean userType;

    @SerializedName("scanTime")
    @Expose
    private boolean scanTime;

    @SerializedName("company")
    @Expose
    private boolean company;

    @SerializedName("temperature")
    @Expose
    private boolean temperature;

    public boolean isPrintBadge() {
        return printBadge;
    }

    public void setPrintBadge(boolean printBadge) {
        this.printBadge = printBadge;
    }

    public boolean isRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(boolean registeredUser) {
        this.registeredUser = registeredUser;
    }

    public boolean isRegisteredVisitor() {
        return registeredVisitor;
    }

    public void setRegisteredVisitor(boolean registeredVisitor) {
        this.registeredVisitor = registeredVisitor;
    }

    public boolean isUnregisteredVisitor() {
        return unregisteredVisitor;
    }

    public void setUnregisteredVisitor(boolean unregisteredVisitor) {
        this.unregisteredVisitor = unregisteredVisitor;
    }

    public boolean isScanImage() {
        return scanImage;
    }

    public void setScanImage(boolean scanImage) {
        this.scanImage = scanImage;
    }

    public boolean isUserName() {
        return userName;
    }

    public void setUserName(boolean userName) {
        this.userName = userName;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public boolean isScanTime() {
        return scanTime;
    }

    public void setScanTime(boolean scanTime) {
        this.scanTime = scanTime;
    }

    public boolean isCompany() {
        return company;
    }

    public void setCompany(boolean company) {
        this.company = company;
    }

    public boolean isTemperature() {
        return temperature;
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "PrintSettingModel{" +
                "printBadge=" + printBadge +
                ", registeredUser=" + registeredUser +
                ", registeredVisitor=" + registeredVisitor +
                ", unregisteredVisitor=" + unregisteredVisitor +
                ", scanImage=" + scanImage +
                ", userName=" + userName +
                ", userType=" + userType +
                ", scanTime=" + scanTime +
                ", company=" + company +
                ", temperature=" + temperature +
                '}';
    }
}
