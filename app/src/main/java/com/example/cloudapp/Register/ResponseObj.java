package com.example.cloudapp.Register;

public class ResponseObj {
    private String createAt;
    private int status;
    private String message;

    public ResponseObj() {
    }

    public ResponseObj(String createAt, int status, String message) {
        this.createAt = createAt;
        this.status = status;
        this.message = message;
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
}
