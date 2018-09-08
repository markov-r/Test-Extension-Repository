package com.telerik.extension_repository.services;

import com.telerik.extension_repository.entities.GitHubData;
import com.telerik.extension_repository.entities.Properties;
import com.telerik.extension_repository.models.ExtensionDto;
import com.telerik.extension_repository.models.PropertiesDto;
import com.telerik.extension_repository.repositories.GitHubRepository;
import com.telerik.extension_repository.services.interfaces.ExtensionService;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import com.telerik.extension_repository.services.interfaces.PropertiesService;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import static com.telerik.extension_repository.utils.Constants.GITHUB_URL;
import static com.telerik.extension_repository.utils.Constants.GIT_KEY;

@Service
public class GithubApiServiceImpl implements GithubApiService/*, InitializingBean */{

    @Autowired
    private ExtensionService extensionService;

    @Autowired
    private GitHubRepository gitHubRepository;

    @Autowired
    private PropertiesService propertiesService;

//    @Override
//    public void afterPropertiesSet() {
//        triggerGitUpdate();
//    }

    @Override
    public GitHub getGHConnection() throws IOException {
        return GitHub.connectUsingOAuth(GIT_KEY);
    }

    public void updateGithubDataAll() {
        List<ExtensionDto> extensionDtoList = this.extensionService.getAllExt();
        for (ExtensionDto extensionDto : extensionDtoList) {
            String fullUrl = extensionDto.getSource_repository_link();
             try {
                GitHubData gitHubData = updateGithubData(fullUrl);
                extensionDto.setGitHubData(gitHubData);
                String pullsCount = gitHubData.getPullsCount();
                String issuesCount = gitHubData.getIssuesCount();
                Date lastCommitDate = gitHubData.getLastCommit();
//                String lastCommit = lastCommitDate.toString();
                Long id = extensionDto.getId();
                this.gitHubRepository.update(pullsCount, issuesCount, lastCommitDate, id);
                 System.out.println("### GH DATA UPDATED ### " + extensionDto.getName());
            } catch (Exception e) {
                 System.out.println(e.getMessage());
                e.getMessage();
            }
        }
    }

//    public class MyThread extends Thread {
//        public void run(){
//            System.out.println("MyThread running");
//        }
//    }

/** Start a new thread for running GitHub synchronization only.
    Thread is set to daemon so that when main thread ends
    this should not stop JVM from shutting down. */

    @PostConstruct
    private void triggerGitUpdate() {

        Thread gitThread = new Thread(() -> {   //TODO: replace anonymous class creation with standard class that extends Thread.
            System.out.println("#######" + Thread.currentThread().getName() + "########");
            while (true) {
                try {
                    updateGithubDataAll();
                    propertiesService.updateLastSuccSync(new Date());
                    PropertiesDto prop = this.propertiesService.getProperties();
                    long interval = prop.getUpdateInterval();
                    System.out.println("INTERVAL IS -> " + interval);
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted!");
                    e.getMessage();
                    propertiesService.updateFailureDetails("Git Data update failed due to an interrupted thread.");
                    propertiesService.updateLastFailedSync(new Date());
                    Thread.currentThread().interrupt();
                }
            }
        });
//        gitThread.setDaemon(true);
        gitThread.start();
    }

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
        return git.getRepository(url).getUpdatedAt();
    }

    @Override
    public String getPullsCount(String url) throws IOException {
        GitHub git = getGHConnection();
        return Integer.toString(git.getRepository(url).getPullRequests(GHIssueState.ALL).size());
    }

}
