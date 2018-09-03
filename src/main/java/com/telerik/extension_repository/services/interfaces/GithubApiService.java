package com.telerik.extension_repository.services.interfaces;

import com.telerik.extension_repository.entities.GitHubData;
import org.kohsuke.github.GitHub;
import java.io.IOException;
import java.util.Date;

public interface GithubApiService {

    GitHub getGHConnection() throws IOException;
    GitHubData updateGithubData(String fullUrl) throws IOException;
    String getOpenIssues(String url) throws IOException;
    Date getCommitDate(String url) throws IOException;
    String getPullsCount(String url) throws IOException;
    void updateGithubDataAll() throws IOException;

}
