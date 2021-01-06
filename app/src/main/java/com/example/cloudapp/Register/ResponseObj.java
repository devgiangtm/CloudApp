package com.example.cloudapp.Register;

public class ResponseObj {
    private String createAt;
    private int status;
    private String message;
    private String locationKey;
    private int tenantId;

    public ResponseObj() {
    }

    public ResponseObj(String createAt, int status, String message, String locationKey, int tenantID) {
        this.createAt = createAt;
        this.status = status;
        this.message = message;
        this.locationKey = locationKey;
        this.tenantId = tenantID;
    }

    public String getLocationKey() {
        return locationKey;
    }

    public void setLocationKey(String locationKey) {
        this.locationKey = locationKey;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
}
