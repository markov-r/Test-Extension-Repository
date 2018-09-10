package com.telerik.extension_repository.entities;

import com.telerik.extension_repository.entities.enums.Status;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(cascade = {CascadeType.ALL}
            , orphanRemoval = true)
    @JoinColumn(name = "git_hub_data_id")
    private GitHubData gitHubData;

    @Column(name = "upload_date")
    private Date uploadDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tags_extensions",
            joinColumns =  {@JoinColumn(name = "extension_id")} ,
            inverseJoinColumns =  {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags;


    public Extension(){
        this.numberOfDownloads = 0;
        this.tags = new HashSet<>();
        this.isFeatured = false;
    }

    public Extension(String name, String description, String version, int numberOfDownloads, String source_repository_link, String fileName, boolean isFeatured, Status status, User owner) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.numberOfDownloads = numberOfDownloads;
        this.source_repository_link = source_repository_link;
        this.fileName = fileName;
        this.isFeatured = isFeatured;
        this.status = status;
        this.owner = owner;
        this.tags = new HashSet<>();
    }

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