package com.example.cloudapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailSettingModel {

    @SerializedName("emailAlert")
    @Expose
    private boolean emailAlert;

    @SerializedName("useCustomStmp")
    @Expose
    private boolean useCustomStmp;

    @SerializedName("emailMask")
    @Expose
    private boolean emailMask;

    @SerializedName("smtpServer")
    @Expose
    private String smtpServer;

    @SerializedName("smtpPort")
    @Expose
    private String smtpPort;

    @SerializedName("smtpUser")
    @Expose
    private String smtpUser;

    @SerializedName("smtpPassword")
    @Expose
    private String smtpPassword;

    @SerializedName("smtpFrom")
    @Expose
    private String smtpFrom;


    @SerializedName("emailRecipient")
    @Expose
    private String emailRecipient;

    @SerializedName("emailRegisterUser")
    @Expose
    private boolean emailRegisterUser;

    @SerializedName("visitorStranger")
    @Expose
    private boolean visitorStranger;

    @SerializedName("showPhoto")
    @Expose
    private boolean showPhoto;

    @SerializedName("smtpSsl")
    @Expose
    private boolean smtpSsl;

    @SerializedName("smtpTls")
    @Expose
    private boolean smtpTls;

    @SerializedName("showUserType")
    @Expose
    private boolean showUserType;

    @SerializedName("sendTestEmail")
    @Expose
    private boolean sendTestEmail;

    public boolean isEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(boolean emailAlert) {
        this.emailAlert = emailAlert;
    }

    public boolean isUseCustomStmp() {
        return useCustomStmp;
    }

    public void setUseCustomStmp(boolean useCustomStmp) {
        this.useCustomStmp = useCustomStmp;
    }

    public boolean isEmailMask() {
        return emailMask;
    }

    public void setEmailMask(boolean emailMask) {
        this.emailMask = emailMask;
    }

    public String getEmailRecipient() {
        return emailRecipient;
    }

    public void setEmailRecipient(String emailRecipient) {
        this.emailRecipient = emailRecipient;
    }

    public boolean isEmailRegisterUser() {
        return emailRegisterUser;
    }

    public void setEmailRegisterUser(boolean emailRegisterUser) {
        this.emailRegisterUser = emailRegisterUser;
    }

    public boolean isVisitorStranger() {
        return visitorStranger;
    }

    public void setVisitorStranger(boolean visitorStranger) {
        this.visitorStranger = visitorStranger;
    }

    public boolean isShowPhoto() {
        return showPhoto;
    }

    public void setShowPhoto(boolean showPhoto) {
        this.showPhoto = showPhoto;
    }

    public boolean isShowUserType() {
        return showUserType;
    }

    public void setShowUserType(boolean showUserType) {
        this.showUserType = showUserType;
    }

    public boolean isSendTestEmail() {
        return sendTestEmail;
    }

    public void setSendTestEmail(boolean sendTestEmail) {
        this.sendTestEmail = sendTestEmail;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public String getSmtpFrom() {
        return smtpFrom;
    }

    public void setSmtpFrom(String smtpFrom) {
        this.smtpFrom = smtpFrom;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public boolean isSmtpSsl() {
        return smtpSsl;
    }

    public void setSmtpSsl(boolean smtpSsl) {
        this.smtpSsl = smtpSsl;
    }

    public boolean isSmtpTls() {
        return smtpTls;
    }

    public void setSmtpTls(boolean smtpTls) {
        this.smtpTls = smtpTls;
    }

    @Override
    public String toString() {
        return "EmailSettingModel{" +
                "emailAlert=" + emailAlert +
                ", useCustomStmp=" + useCustomStmp +
                ", emailMask=" + emailMask +
                ", smtpServer='" + smtpServer + '\'' +
                ", smtpPort='" + smtpPort + '\'' +
                ", smtpUser='" + smtpUser + '\'' +
                ", smtpPassword='" + smtpPassword + '\'' +
                ", emailRecipient='" + emailRecipient + '\'' +
                ", emailRegisterUser=" + emailRegisterUser +
                ", visitorStranger=" + visitorStranger +
                ", showPhoto=" + showPhoto +
                ", smtpSsl=" + smtpSsl +
                ", smtpTls=" + smtpTls +
                ", showUserType=" + showUserType +
                ", sendTestEmail=" + sendTestEmail +
                '}';
    }
}
