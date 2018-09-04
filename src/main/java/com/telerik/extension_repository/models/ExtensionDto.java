package com.telerik.extension_repository.models;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.viewModels.tags.TagView;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

public class ExtensionDto {

    private Long id;

    private String name;

    private String description;

    private int numberOfDownloads;

    private String tagString;

    private Set<TagView> tags;

   // private byte[] file;

    private MultipartFile file;

   // private String fileName;

    private LoggedUser owner;

    private Status status;

    private Long version;

    private String downloadLink;

    private String source_repository_link;

    private boolean isFeatured;

    private GitHubData gitHubData;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(int numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public GitHubData getGitHubData() {
        return gitHubData;
    }

    public void setGitHubData(GitHubData gitHubData) {
        this.gitHubData = gitHubData;
    }

    public Set<TagView> getTags() {
        return tags;
    }

    public void setTags(Set<TagView> tags) {
        this.tags = tags;
    }

    public LoggedUser getOwner() {
        return owner;
    }

    public void setOwner(LoggedUser owner) {
        this.owner = owner;
    }

    public String getSource_repository_link() {
        return source_repository_link;
    }

    public void setSource_repository_link(String source_repository_link) {
        this.source_repository_link = source_repository_link;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTagString() {
        return tagString;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }
}
