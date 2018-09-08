package com.telerik.extension_repository.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="git_hub_data")
public class GitHubData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "gitHubData")
    private Extension extension;

    @Column(name = "num_of_pulls")
    private String pullsCount;

    @Column(name = "num_of_issues")
    private String issuesCount;

    @Column(name = "latest_commit")
    private Date lastCommit;

    public GitHubData(){
    }

    public GitHubData(Extension extension, String pullsCount, String issuesCount, Date lastCommit) {
        this.extension = extension;
        this.pullsCount = pullsCount;
        this.issuesCount = issuesCount;
        this.lastCommit = lastCommit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public String getPullsCount() {
        return pullsCount;
    }

    public void setPullsCount(String pullCount) {
        this.pullsCount = pullCount;
    }

    public String getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(String issuesCount) {
        this.issuesCount = issuesCount;
    }

    public Date getLastCommit() {
        return lastCommit;
    }

    public void setLastCommit(Date lastCommit) {
        this.lastCommit = lastCommit;
    }
}
