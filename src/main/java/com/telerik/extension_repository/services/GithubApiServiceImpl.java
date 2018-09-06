package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.Properties;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.repositories.GitHubRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import com.telerik.extension_repository.services.interfaces.PropertiesService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static com.telerik.extension_repository.utils.Constants.*;

@Service
public class GithubApiServiceImpl implements GithubApiService {

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private GitHubRepository gitHubRepository;

    @Autowired
    private PropertiesService propertiesService;

//    @Autowired
//    public GithubApiServiceImpl(ExtensionService extensionService, GitHubRepository gitHubRepository) {
//        this.extensionService = extensionService;
//        this.gitHubRepository = gitHubRepository;
//    }

    public GithubApiServiceImpl() {

    }

    @Override
    public GitHub getGHConnection() throws IOException {
        return GitHub.connectUsingOAuth(GIT_KEY);
    }

    public void updateGithubDataAll() {
        List<ExtensionDto> extensionDtoList = extensionService.getAllExt();
        for (ExtensionDto extensionDto : extensionDtoList) {
            String fullUrl = extensionDto.getSource_repository_link();
            GitHubData gitHubData = null;
            try {
                gitHubData = updateGithubData(fullUrl);
                extensionDto.setGitHubData(gitHubData);
                String pullsCount = gitHubData.getPullsCount();
                String issuesCount = gitHubData.getIssuesCount();
                Date lastCommitDate = gitHubData.getLastCommit();
                String lastCommit = lastCommitDate.toString();
                Long id = extensionDto.getId();
                this.gitHubRepository.update(pullsCount, issuesCount, lastCommit, id);

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

//    public class MyThread extends Thread {
//        public void run(){
//            System.out.println("MyThread running");
//        }
//    }

    @PostConstruct
    public void startGitUpdate() {
        Properties properties = this.propertiesService.getProperties();
        long interval = properties.getUpdateInterval();
        Thread gitThread = new Thread(() -> {
            System.out.println( Thread.currentThread().getName());
            while (true) {
                try {
                    updateGithubDataAll();
                    propertiesService.updateLastSuccSync(new Date());
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interupted!");
                    e.getMessage();
                    propertiesService.updateFailureDetails("Git Data update failed due to an interrupted thread.");
                    propertiesService.updateLastFailedSync(new Date());
                }
            }
        });
        gitThread.setDaemon(true);
        gitThread.start();
    }

//    public void changeInterval()

    @Override
    public GitHubData updateGithubData(String fullUrl) throws IOException {
        String url = fullUrl.substring(GITHUB_URL.length());
        GitHubData gitHubData = new GitHubData();
        gitHubData.setIssuesCount(getOpenIssues(url));
        gitHubData.setLastCommit(getCommitDate(url));
        gitHubData.setPullsCount(getPullsCount(url));
        return gitHubData;
    }

    @Override
    public String getOpenIssues(String url) throws IOException {
        GitHub git = getGHConnection();
        return Integer.toString(git.getRepository(url).getOpenIssueCount());
    }

    @Override
    public Date getCommitDate(String url) throws IOException {
        GitHub git = getGHConnection();
//        return null;
        return git.getRepository(url).getUpdatedAt();
    }

    @Override
    public String getPullsCount(String url) throws IOException {
        GitHub git = getGHConnection();
        return Integer.toString(git.getRepository(url).getPullRequests(GHIssueState.ALL).size());
    }
}
