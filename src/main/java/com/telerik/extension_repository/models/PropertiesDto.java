package com.telerik.extension_repository.models;

import java.util.Date;

public class PropertiesDto {

    private String failureDetails;

    private Date lastFailedSync;

    private Date lastSuccSync;

    private long updateInterval;


    public String getFailureDetails() {
        return failureDetails;
    }

    public void setFailureDetails(String failureDetails) {
        this.failureDetails = failureDetails;
    }

    public Date getLastFailedSync() {
        return lastFailedSync;
    }

    public void setLastFailedSync(Date lastFailedSync) {
        this.lastFailedSync = lastFailedSync;
    }

    public Date getLastSuccSync() {
        return lastSuccSync;
    }

    public void setLastSuccSync(Date lastSuccSync) {
        this.lastSuccSync = lastSuccSync;
    }

    public long getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(long updateInterval) {
        this.updateInterval = updateInterval;
    }
}
