package com.telerik.extension_repository.models.bindingModels.extensions;

import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.viewModels.tags.TagView;

import java.util.Set;

public class EditExtensionModel {
    private Long id;

    private String name;

    private String description;

    private String download_link;

    private byte[] file;

    private Set<TagView> tags;

    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Set<TagView> getTags() {
        return tags;
    }

    public void setTags(Set<TagView> tags) {
        this.tags = tags;
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
