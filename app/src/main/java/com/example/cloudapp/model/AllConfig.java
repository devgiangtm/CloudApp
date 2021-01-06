package com.example.cloudapp.model;

public class AllConfig {
    private MipsConfig mipsConfig;
    private EmailSettingModel emailConfig;
    private PrintSettingModel printConfig;
    private CDCSettingModel cdcConfig;
    private UpdateSettingModel updateConfig;

    public AllConfig() {

    }

    public AllConfig(MipsConfig mipsConfig, EmailSettingModel emailConfig, PrintSettingModel printConfig, CDCSettingModel cdcConfig, UpdateSettingModel updateConfig) {
        this.mipsConfig = mipsConfig;
        this.emailConfig = emailConfig;
        this.printConfig = printConfig;
        this.cdcConfig = cdcConfig;
        this.updateConfig = updateConfig;
    }

    public MipsConfig getMipsConfig() {
        return mipsConfig;
    }

    public void setMipsConfig(MipsConfig mipsConfig) {
        this.mipsConfig = mipsConfig;
    }

    public EmailSettingModel getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailSettingModel emailConfig) {
        this.emailConfig = emailConfig;
    }

    public PrintSettingModel getPrintConfig() {
        return printConfig;
    }

    public void setPrintConfig(PrintSettingModel printConfig) {
        this.printConfig = printConfig;
    }

    public CDCSettingModel getCdcConfig() {
        return cdcConfig;
    }

    public void setCdcConfig(CDCSettingModel cdcConfig) {
        this.cdcConfig = cdcConfig;
    }

    public UpdateSettingModel getUpdateConfig() {
        return updateConfig;
    }

    public void setUpdateConfig(UpdateSettingModel updateConfig) {
        this.updateConfig = updateConfig;
    }
}
