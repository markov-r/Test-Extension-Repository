package com.telerik.extension_repository;

import com.telerik.extension_repository.services.GithubApiServiceImpl;
import com.telerik.extension_repository.utils.StorageProperties;
import com.telerik.extension_repository.services.interfaces.GithubApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ExtensionRepositoryApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ExtensionRepositoryApplication.class, args);
       // GithubApiService ghs = new GithubApiServiceImpl();
        //ghs.updateGithubDataAll();


//        GitHub gitHub = GitHub.connectUsingOAuth("5c1a77eec3047ae6b562a55a7c0e4d4735cb38ef");
//        gitHub.getRepository("https://github.com/Petroslav/telerik-alpha-final-project").get
    }

//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
//        };
//    }
}
