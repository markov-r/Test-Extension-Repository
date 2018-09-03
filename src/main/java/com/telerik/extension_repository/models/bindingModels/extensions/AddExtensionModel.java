package com.telerik.extension_repository.models.bindingModels.extensions;

import com.telerik.extension_repository.entities.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AddExtensionModel {
    private String name;

    private String description;

    private String download_link;

    private Status status;

    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownload_link() {

        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
