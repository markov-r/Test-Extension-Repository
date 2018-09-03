package com.telerik.extension_repository.models.viewModels.log;

import com.telerik.extension_repository.entities.enums.Operation;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;

import java.util.Date;

public class LogView {
    private LoggedUser user;
    private String tableName;
    private Operation operation;
    private Date date;

    public LoggedUser getUser() {
        return user;
    }

    public void setUser(LoggedUser user) {
        this.user = user;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
