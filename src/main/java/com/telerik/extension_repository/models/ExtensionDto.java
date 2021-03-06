package com.telerik.extension_repository.models;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import com.telerik.extension_repository.models.bindingModels.user.LoggedUser;
import com.telerik.extension_repository.models.bindingModels.user.RegisterUserModel;
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

    private String filename;

    private MultipartFile file;

    private RegisterUserModel owner;

    private Status status;

    private String version;

    private String source_repository_link;

    private boolean isFeatured;

    private GitHubData gitHubData;

    private Date uploadDate;

    public ExtensionDto(String name,
                        String description,
                        String version,
                        int numberOfDownloads,
                        String source_repository_link,
                        String filename,
                        boolean isFeatured,
                        Status status,
                        RegisterUserModel author) {

        this.name = name;
        this.description = description;
        this.version = version;
        this.numberOfDownloads = numberOfDownloads;
        this.source_repository_link = source_repository_link;
        this.filename = filename;
        this.isFeatured = isFeatured;
        this.status = status;
        this.owner = author;
    }

    public ExtensionDto(String name,
                        String description,
                        Set<TagView> tags,
                        RegisterUserModel owner,
                        Status status,
                        String version,
                        String source_repository_link,
                        boolean isFeatured, Date uploadDate) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.owner = owner;
        this.status = status;
        this.version = version;
        this.source_repository_link = source_repository_link;
        this.isFeatured = isFeatured;
        this.uploadDate = uploadDate;
    }

    public ExtensionDto() {
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(int numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public RegisterUserModel getOwner() {
        return owner;
    }

    public void setOwner(RegisterUserModel owner) {
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

}
