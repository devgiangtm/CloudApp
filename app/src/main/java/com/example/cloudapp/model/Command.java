package com.example.cloudapp.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Command")
public class Command {

    @DatabaseField(id = true)
    private Long createdAt;
    @DatabaseField (columnName = "tenantid")
    private int tenantId;
    @DatabaseField (columnName = "metadate")
    private String metaDate;
    @DatabaseField (columnName = "command")
    private String command;

    public Command(String command, Long createdAt, int tenantId, String metaDate) {
        this.command = command;
        this.createdAt = createdAt;
        this.tenantId = tenantId;
        this.metaDate = metaDate;
    }

    public Command() {
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getMetaDate() {
        return metaDate;
    }

    public void setMetaDate(String metaDate) {
        this.metaDate = metaDate;
    }
}
