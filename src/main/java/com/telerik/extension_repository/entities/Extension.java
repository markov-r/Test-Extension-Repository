package com.telerik.extension_repository.entities;

import com.telerik.extension_repository.entities.enums.Status;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "extensions")
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String version;

    @Column(name = "number_of_downloads")
    private int numberOfDownloads;

    private String source_repository_link;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "is_featured")
    private boolean isFeatured;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "git_hub_data_id")
    private GitHubData gitHubData;

    @Column(name = "upload_date")
    private Date uploadDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_extensions",
            joinColumns =  {@JoinColumn(name = "extension_id")} ,
            inverseJoinColumns =  {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;

    public Long getId() {
        return id;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSource_repository_link() {
        return source_repository_link;
    }

    public void setSource_repository_link(String source_repository_link) {
        this.source_repository_link = source_repository_link;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(int numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public GitHubData getGitHubData() {
        return gitHubData;
    }

    public void setGitHubData(GitHubData gitHubData) {
        this.gitHubData = gitHubData;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

}