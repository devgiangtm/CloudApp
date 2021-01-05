package com.example.cloudapp.Register;

public class RegisterInfo {
    private String ipAddress;
    private String macAddress;
    private String hashingMac;

    public RegisterInfo() {
    }

    public RegisterInfo(String ipAddress, String macAddress) {
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
    }

    public String getHashingMac() {
        return hashingMac;
    }

    public void setHashingMac(String hashingMac) {
        this.hashingMac = hashingMac;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
