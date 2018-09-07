package com.telerik.extension_repository.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="system_properties")
public class Properties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "update_interval", nullable = false)
    private long updateInterval;

    @Column(name = "last_succ_sync", nullable = false)
    private Date lastSuccSync;

    @Column(name = "last_failed_sync", nullable = false)
    private Date lastFailedSync;

    @Column(name = "failure_details", nullable = false)
    private String failureDetails;

    @Column(name = "version")
    private long version;

    public Properties(){}

    public Properties(long updateInterval, Date lastSuccSync, Date lastFailedSync, String failureDetails) {
        this.updateInterval = updateInterval;
        this.lastSuccSync = lastSuccSync;
        this.lastFailedSync = lastFailedSync;
        this.failureDetails = failureDetails;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(long updateInterval) {
        this.updateInterval = updateInterval;
    }

    public Date getLastSuccSync() {
        return lastSuccSync;
    }

    public void setLastSuccSync(Date lastSuccSync) {
        this.lastSuccSync = lastSuccSync;
    }

    public Date getLastFailedSync() {
        return lastFailedSync;
    }

    public void setLastFailedSync(Date lastFailedSync) {
        this.lastFailedSync = lastFailedSync;
    }

    public String getFailureDetails() {
        return failureDetails;
    }

    public void setFailureDetails(String failureDetails) {
        this.failureDetails = failureDetails;
    }
}