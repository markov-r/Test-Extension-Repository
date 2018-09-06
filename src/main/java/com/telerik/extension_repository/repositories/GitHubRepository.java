package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.GitHubData;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface GitHubRepository extends JpaRepository<GitHubData, Long> {

    GitHubData save(GitHubData gitHubData);

    void deleteById(long id);

    @Query(value =
            "SELECT g FROM GitHubData AS g " +
                    "ORDER BY g.id")
    List<GitHubData> getAll();

    @Query(value =
            "SELECT g FROM GitHubData AS g " +
                    "WHERE g.id = :id")
    GitHubData getById(@Param("id")int id);

    @Transactional
    @Modifying
    @Query ("UPDATE GitHubData " +
            "SET pullsCount = :pullsCount, issuesCount = :issuesCount, lastCommit = :lastCommit WHERE id =:id")
    void update(@Param("pullsCount") String pullsCount, @Param("issuesCount") String issuesCount,
                @Param("lastCommit") Date lastCommit, @Param("id") long id);

}